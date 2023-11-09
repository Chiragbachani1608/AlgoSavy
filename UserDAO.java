import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static int registerUser(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (full_name, email, phone, city) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getFullName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPhone());
                statement.setString(4, user.getCity());

                statement.executeUpdate();

                // Retrieve the generated user ID
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                    return user.getUserId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indicates an error
    }
}
