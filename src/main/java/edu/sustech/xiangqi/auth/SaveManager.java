package edu.sustech.xiangqi.auth;

import edu.sustech.xiangqi.auth.SessionManager;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaveManager {
    // 存档管理
    // 存档文件存储：./saves/<user_name>/<slots>.dat"

    private static final String SAVE_ROOT = "saves";
    private static final String EXT = ".dat";

    public static final String DEFAULT_SLOT = "default";

    private SaveManager() {
    }

    /** 当前是否允许使用存档功能。游客和未登录都返回 false。 */
    public static boolean canUseSave() {
        return !SessionManager.isGuest() && SessionManager.getCurrentUser() != null;
    }

    private static void requireLoggedInUser() {
        if (!canUseSave()) {
            throw new IllegalStateException("游客模式或未登录，禁止存档/读档");
        }
    }

    /** 当前用户自己的存档目录。 */
    private static Path userSaveDir() throws IOException {
        requireLoggedInUser();

        String username = SessionManager.getCurrentUser();

        // 防止用户名造成路径穿越。允许中文、字母、数字、下划线、短横线。
        if (username == null || !username.matches("[\\p{L}\\p{N}_-]{1,32}")) {
            throw new IllegalArgumentException("当前用户名不能用于存档目录");
        }

        Path dir = Paths.get(SAVE_ROOT, username);
        Files.createDirectories(dir);
        return dir;
    }

    /** 校验槽位名并得到存档文件路径。 */
    private static Path slotFile(String slot) throws IOException {
        if (slot == null || !slot.matches("[\\p{L}\\p{N}_-]{1,32}")) {
            throw new IllegalArgumentException("非法存档槽名");
        }
        return userSaveDir().resolve(slot + EXT);
    }

    /**
     * 存档。
     * @param slot 存档槽，例如 "slot1"、"auto"、"default"
     * @param data 棋局状态对象，必须实现 Serializable
     */
    public static void save(String slot, Serializable data) throws IOException {
        if (data == null) {
            throw new IllegalArgumentException("存档数据不能为空");
        }

        Path target = slotFile(slot);
        Path tmp = target.resolveSibling(target.getFileName() + ".tmp");

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(tmp)))) {
            out.writeObject(data);
        }

        // 先写临时文件，再替换，降低写一半损坏存档的风险。
        try {
            Files.move(tmp, target,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 读档。
     * @return 没有该存档时返回 null
     */
    public static <T> T load(String slot, Class<T> type)
            throws IOException, ClassNotFoundException {

        Path file = slotFile(slot);
        if (!Files.isRegularFile(file)) {
            return null;
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(Files.newInputStream(file)))) {
            Object obj = in.readObject();
            if (!type.isInstance(obj)) {
                throw new IOException("存档类型不匹配，期望：" + type.getName());
            }
            return type.cast(obj);
        }
    }

    /** 当前用户是否存在某个槽位的存档。 */
    public static boolean exists(String slot) throws IOException {
        return Files.isRegularFile(slotFile(slot));
    }

    /** 列出当前用户所有存档槽名。 */
    public static List<String> listSlots() throws IOException {
        Path dir = userSaveDir();

        try (Stream<Path> stream = Files.list(dir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .filter(name -> name.endsWith(EXT))
                    .map(name -> name.substring(0, name.length() - EXT.length()))
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    /** 删除当前用户的某个存档。 */
    public static boolean delete(String slot) throws IOException {
        return Files.deleteIfExists(slotFile(slot));
    }
}
