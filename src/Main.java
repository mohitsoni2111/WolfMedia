import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    static final String USERNAME = "spatkar2";
    static final String PW = "200478608";
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/spatkar2";
    // Put your oracle ID and password here

    public static Connection connection = null;
    public static Statement statement = null;
    public static final ResultSet result = null;

    /**
     * This function prints a menu to the system console.
     * Through this menu, one can interact with this program.
     * The first step must always be to load the database ("Initialize/Reload Database"),
     * if it is not already loaded. Then one can either look at reports,
     * handle payments, or otherwise interact with the database.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            connectToDatabase();

            // Display the main menu
            boolean exit = false;
            while (!exit) {
                System.out.println("Menu:");
                System.out.println("1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Update");
                System.out.println("4. Payment");
                System.out.println("5. Reports");
                System.out.println("6. Initialize/Reload Database");
                System.out.println("7. Exit()");

                int choice;
                while(true) {
                    try {
                        System.out.print("Enter your choice: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        break;
                    }
                    catch(Exception e) {
                        System.out.println("Please enter a valid choice (numerical)");
                    }
                }

                // Perform the selected action
                switch (choice) {
                    case 1 -> insertMenu();
                    case 2 -> deleteMenu();
                    case 3 -> updateMenu();
                    case 4 -> payment();
                    case 5 -> reportsMenu();
                    case 6 -> initialize();
                    case 7 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("\nCLOSING the Database connection finally!");
            close();
        }
    }

    /**
     * This is a sub-menu for the Payments option.
     * @throws SQLException
     */
    public static void payment() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Insert Menu:");
        System.out.println("1. Pay Record Label");
        System.out.println("2. Pay Podcast Host");
        System.out.println("3. Make Royalty payment to Artist");
        System.out.println("4. Receive subscription payment from subscribers");

        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        // Perform the selected action
        switch (choice) {
            case 1 -> servicePaysAllRecordLabels();
            case 2 -> servicePaysAllPodcastHosts();
            case 3 -> recordLabelsPayArtists();
            case 4 -> allUsersPayStreamingService();
        }
    }

    /**
     * This is a sub-menu for the Insert option.
     * @throws SQLException
     */

    public static void insertMenu() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Insert Menu:");
        System.out.println("1. Insert a Song Genre");
        System.out.println("2. Insert a Record Label");
        System.out.println("3. Insert a Album");
        System.out.println("4. Insert a Artist");
        System.out.println("5. Insert a Song");
        System.out.println("6. Insert a Podcast Genre");
        System.out.println("7. Insert a Podcast Host");
        System.out.println("8. Insert a Podcast");
        System.out.println("9. Insert a Podcast Episode");
        System.out.println("10. Insert a User");

        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        // Perform the selected action
        switch (choice) {
            case 1 -> insertSongGenre();
            case 2 -> insertRecordLabel();
            case 3 -> insertAlbum();
            case 4 -> insertArtist();
            case 5 -> insertSong();
            case 6 -> insertPodcastGenre();
            case 7 -> insertPodcastHost();
            case 8 -> insertPodcast();
            case 9 -> insertPodcastEpisode();
            case 10 -> insertUser();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * This is a sub-menu for financial reports.
     * @throws SQLException
     */
    public static void reportsMenuFinancial() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Financial Reports Menu:");
        System.out.println("1. ALL payments reports");
        System.out.println("2. ALL streaming service revenue reports");
        System.out.println("3. TOTAL: Streaming service revenue.");
        System.out.println("4. MONTHLY: Streaming service revenue.");
        System.out.println("5. ANNUAL: Streaming service revenue.");
        System.out.println("6. TOTAL: Record label payments.");
        System.out.println("7. MONTHLY: Record label payments.");
        System.out.println("8. ANNUAL: Record label payments.");
        System.out.println("9. TOTAL: Podcast host payments.");
        System.out.println("10. MONTHLY: Podcast host payments..");
        System.out.println("11. ANNUAL: Podcast host payments.");
        System.out.println("12. TOTAL: Artists payments.");
        System.out.println("13. MONTHLY: Artists payments.");
        System.out.println("14. ANNUAL: Artists payments.");

        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        // Perform the selected action
        switch (choice) {
            case 1 -> printAllFinancialReports();
            case 2 -> printAllServiceRevenueReports();
            case 3 -> printServiceTotalRevenue();
            case 4 -> printServiceMonthlyRevenue();
            case 5 -> printServiceAnnualRevenue();
            case 6 -> printRecordLabelTotalPayments();
            case 7 -> printRecordLabelMonthlyPayments();
            case 8 -> printRecordLabelAnnualPayments();
            case 9 -> printPodcastHostTotalPayments();
            case 10 -> printPodcastHostMonthlyPayments();
            case 11 -> printPodcastHostAnnualPayments();
            case 12 -> printArtistTotalPayments();
            case 13 -> printArtistMonthlyPayments();
            case 14 -> printArtistAnnualPayments();
        }
    }

    /**
     * This is a sub-menu for the main Reports option.
     * It appears in the system console.
     * @throws SQLException
     */
    public static void reportsMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Reports Menu:");
        System.out.println("1. FINANCIAL REPORTS");
        System.out.println("2. Songs given the Artist.");
        System.out.println("3. Songs given the Album.");
        System.out.println("4. PLAY COUNT: by Song. Continue to select MONTHLY/YEARLY/TOTAL.");
        System.out.println("5. PLAY COUNT: Monthly, by Artist.");
        System.out.println("6. PLAY COUNT: Monthly, by Album.");
        System.out.println("7. Payments In Time Period: PodcastHosts");
        System.out.println("8. Payments In Time Period: RecordLabels");
        System.out.println("9. Payments In Time Period: Artists");
        System.out.println("10. Podcast Episodes For: Podcast");
        System.out.println("11. Podcast Episodes For: Podcast Host");
        System.out.println("12. PODCAST SUBSCRIBER/RATING REPORTS: Monthly");
        System.out.println("13. PODCAST SUBSCRIBER/RATING REPORTS: Yearly");
        System.out.println("14. PODCAST SUBSCRIBER/RATING REPORTS: Total");

        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        // Perform the selected action
        switch (choice) {
            case 1:
                reportsMenuFinancial();
                break;
            case 2:
                printSongsForArtist();
                break;
            case 3:
                printSongsForAlbum();
                break;
            case 4:
                playCountsBySongReportsMenu();
                break;
            case 5:
                printMonthlyPlayCountsForArtists();
                break;
            case 6:
                printMonthlyPlayCountsForAlbums();
                break;
            case 7:
                //Scanner scanner = new Scanner(System.in);
                System.out.println("Enter START TIME (YYYY-MM-DD): ");
                String startTimePH = scanner.next();
                System.out.println("Enter END TIME (YYYY-MM-DD): ");
                String endTimePH = scanner.next();
                printPaymentsInTimePeriodPodcastHosts(startTimePH, endTimePH);
                break;
            case 8:
                System.out.println("Enter START TIME (YYYY-MM-DD): ");
                String startTimeRL = scanner.next();
                System.out.println("Enter END TIME (YYYY-MM-DD): ");
                String endTimeRL = scanner.next();
                printPaymentsInTimePeriodRecordLabels(startTimeRL, endTimeRL);
                break;
            case 9:
                System.out.println("Enter START TIME (YYYY-MM-DD): ");
                String startTimeAR = scanner.next();
                System.out.println("Enter END TIME (YYYY-MM-DD): ");
                String endTimeAR = scanner.next();
                printPaymentsInTimePeriodArtists(startTimeAR, endTimeAR);
                break;
            case 10:
                showAllPodcasts();
                System.out.println("Enter podcast ID (see above for options list): ");
                String podcastId = scanner.next();
                printPodcastEpisodesForPodcast(podcastId);
                break;
            case 11:
                showAllPodcastHosts();
                System.out.println("Enter podcast host ID (see above for options list): ");
                String podcastHostId = scanner.next();
                printPodcastEpisodesForPodcastHost(podcastHostId);
                break;
            case 12:
                printPodcastSubscriberRatingReportsMonthly();
                break;
            case 13:
                printPodcastSubscriberRatingReportsYearly();
                break;
            case 14:
                printPodcastSubscriberRatingReportsTotal();
                break;
        }
    }

    /**
     * This prints out monthly reports for podcast subscriber counts and average ratings.
     * @throws SQLException
     */
    public static void printPodcastSubscriberRatingReportsMonthly() throws SQLException {
        System.out.println("Podcast Reports for subscriber count and ratings: MONTHLY:");
        String query = "SELECT podcastId, SUM(totalSubscribers) totalSubscribers, AVG(rating) ratingAverage, DATE_FORMAT(podcastLogMonthYear, '%b %Y') AS podcastLogMonth FROM PodcastLogs\n" +
                "    GROUP BY podcastId, podcastLogMonth;";
        PreparedStatement myStmt = connection.prepareStatement(query);
        ResultSet rs = myStmt.executeQuery();
        DBTablePrinter.printResultSet(rs);
    }
    /**
     * This prints out annual reports for podcast subscriber counts and average ratings.
     * @throws SQLException
     */
    public static void printPodcastSubscriberRatingReportsYearly() throws SQLException {
        System.out.println("Podcast Reports for subscriber count and ratings: YEARLY:");
        String query = "SELECT podcastId, SUM(totalSubscribers) totalSubscribers, AVG(rating) ratingAverage, YEAR(podcastLogMonthYear) AS podcastLogYear FROM PodcastLogs\n" +
                "    GROUP BY podcastId, podcastLogYear;";
        PreparedStatement myStmt = connection.prepareStatement(query);
        ResultSet rs = myStmt.executeQuery();
        DBTablePrinter.printResultSet(rs);
    }
    /**
     * This prints out total reports for podcast subscriber counts and average ratings.
     * @throws SQLException
     */
    public static void printPodcastSubscriberRatingReportsTotal() throws SQLException {
        System.out.println("Podcast Reports for subscriber count and ratings: TOTAL:");
        String query = "SELECT podcastId, SUM(totalSubscribers) totalSubscribers, AVG(rating) ratingAverage FROM PodcastLogs\n" +
                "    GROUP BY podcastId;";
        PreparedStatement myStmt = connection.prepareStatement(query);
        ResultSet rs = myStmt.executeQuery();
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * This is another sub-menu for the system console that allows one to select
     * whether they would like to view monthly, yearly, or total song play counts.
     * @throws SQLException
     */
    public static void playCountsBySongReportsMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Select one option for PLAY COUNT, by Song:");
        System.out.println("1. MONTHLY.");
        System.out.println("2. YEARLY.");
        System.out.println("3. TOTAL.");
        int choice;
        while(true) {
            try {
                System.out.print("Enter a choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        switch (choice) {
            case 1:
                printMonthlyPlayCountsForSongs();
                break;
            case 2:
                printYearlyPlayCountsForSongs();
                break;
            case 3:
                printTotalPlayCountsForSongs();
                break;
        }
    }

    /**
     * This is a general print table function.
     * @param tableName
     * @throws SQLException
     */
    public static void printTable(String tableName) throws SQLException {
        String query = "Select * from " + tableName;
        PreparedStatement myStmt = connection.prepareStatement(query);
        ResultSet rs = myStmt.executeQuery();
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * This function prints all the songs belonging to a given artist.
     * @throws SQLException
     */
    public static void printSongsForArtist() throws SQLException {
        Scanner artistIdScanner = new Scanner(System.in);
        printTable("Artists");

        System.out.print("Enter an artist ID from the list above: ");
        String artistId = "'" + artistIdScanner.next() + "'";
        String query = "SELECT Songs.* FROM Artists\n" +
                "    LEFT JOIN Sings on Artists.artistID = Sings.artistID\n" +
                "    LEFT JOIN Songs on Sings.songID = Songs.songID\n" +
                "    WHERE Sings.artistID = " + artistId;
        ResultSet rs = statement.executeQuery(query);
        System.out.format("Songs for artist with id: %s\n", artistId);
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * This function prints all the songs belonging to a given album.
     * @throws SQLException
     */
    public static void printSongsForAlbum() throws SQLException {
        Scanner albumIdScanner = new Scanner(System.in);
        printTable("Albums");

        System.out.println("Enter an album ID from the list above: ");
        String albumId = "'" + albumIdScanner.next() + "'";
        String query = "SELECT Songs.* FROM Songs\n" +
                "    LEFT JOIN Albums on Songs.albumID = Albums.albumID\n" +
                "    WHERE Albums.albumID = " + albumId;
        ResultSet rs = statement.executeQuery(query);
        DBTablePrinter.printResultSet(rs);
    }

    public static boolean userExists(String userID) throws SQLException {
        boolean sExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE userID = \'" + userID + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            sExists = true;
        }
        return sExists;
    }

    public static boolean earnerExists(String earnerID) throws SQLException {
        boolean sExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Earners WHERE earnerID = \'" + earnerID + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            sExists = true;
        }
        return sExists;
    }

    public static boolean songExists(String songId) throws SQLException {
        boolean sExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Songs WHERE songID = \'" + songId + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            sExists = true;
        }
        return sExists;
    }

    public static boolean songsLogExists(String songId) throws SQLException {
        boolean sExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM SongsLogs WHERE songID = \'" + songId + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            sExists = true;
        }
        return sExists;
    }


    public static boolean artistExists(String artistId) throws SQLException {
        boolean arExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Artists WHERE artistID = \'" + artistId + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            arExists = true;
        }
        return arExists;
    }

    public static boolean albumExists(String albumId) throws SQLException {
        boolean alExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Albums WHERE albumID = \'" + albumId + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            alExists = true;
        }
        return alExists;
    }

    public static boolean songGenreExists(String songGenreName) throws SQLException {
        boolean alExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM SongGenres WHERE songGenreName = \'" + songGenreName + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            alExists = true;
        }
        return alExists;
    }

    public static boolean recordLabelExists(String earnerID) throws SQLException {
        boolean alExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM RecordLabels WHERE earnerID = \'" + earnerID + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            alExists = true;
        }
        return alExists;
    }

    public static boolean specialGuestExists(String guestName) throws SQLException {
        boolean pExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM SpecialGuests WHERE guestName = \'" + guestName + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            pExists = true;
        }
        return pExists;
    }

    public static boolean podcastSponsorExists(String podcastSponsorName) throws SQLException {
        boolean pExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM PodcastSponsors WHERE podcastSponsorName = \'" + podcastSponsorName + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            pExists = true;
        }
        return pExists;
    }

    public static boolean podcastGenreExists(String podcastGenreName) throws SQLException {
        boolean pExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM PodcastGenres WHERE podcastGenreName = \'" + podcastGenreName + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            pExists = true;
        }
        return pExists;
    }

    public static boolean podcastExists(String podcastID) throws SQLException {
        boolean pExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM Podcasts WHERE podcastId = \'" + podcastID + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            pExists = true;
        }
        return pExists;
    }

    public static boolean podcastHostExists(String podcastHostId) throws SQLException {
        boolean phExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM PodcastHosts WHERE podcastHostEarnerId = \'" + podcastHostId + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            phExists = true;
        }
        return phExists;
    }

    public static boolean podcastEpisodeExists(String podcastEpisodeID) throws SQLException {
        boolean peExists = false;
        ResultSet rs = statement.executeQuery("SELECT * FROM PodcastEpisodes WHERE podcastEpisodeId = \'" + podcastEpisodeID + "\'");
        if (rs.next()) {
            // If the query returned a row, the song exists
            peExists = true;
        }
        return peExists;
    }

    public static boolean doesRecordExists(String tableName, String id) throws SQLException {
        boolean recordExists = false;

        switch (tableName.toLowerCase()) {
            case "users" -> {
                return userExists(id);
            }
            case "earners" -> {
                return earnerExists(id);
            }
            case "artists" -> {
                return artistExists(id);
            }
            case "recordlabels" -> {
                return recordLabelExists(id);
            }
            case "songs" -> {
                return songExists(id);
            }
            case "songslogs" -> {
                return songsLogExists(id);
            }
            case "albums" -> {
                return albumExists(id);
            }
            case "specialguests" -> {
                return specialGuestExists(id);
            }
            case "songgenres" -> {
                return songGenreExists(id);
            }
            case "podcastsponsors" -> {
                return podcastSponsorExists(id);
            }
            case "podcastgenres" -> {
                return podcastGenreExists(id);
            }
            case "podcastepisodes" -> {
                return podcastEpisodeExists(id);
            }
            case "podcasts" -> {
                return podcastExists(id);
            }
            case "podcasthosts" -> {
                return podcastHostExists(id);
            }
            default -> System.out.println("No such table exists");
        }
        return recordExists;
    }


    /**
     * @Insert Insert a new Earner in the database.
     * @throws SQLException
     */
    public static void insertEarner() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Existing Earners are: \n");
        printTable("Earners");

        System.out.print("\n Enter the new earner's Id: ");
        String earnerID = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Earners");
            uprs.moveToInsertRow();
            uprs.updateString("earnerID", earnerID);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("****************************************************************");
        System.out.println("Album added successfully with id: " + earnerID + " in the database.");
        System.out.println("****************************************************************");

        System.out.println("After insertion, Earners table: \n");
        printTable("Earners");
    }

    /**
     *
     * @param earnerID
     * @Insert Insert a new Earner with "earnerID" in the database.
     * @throws SQLException
     */
    public static void insertEarner(String earnerID) throws SQLException{
        System.out.println("\nExisting Earners are: ");
        printTable("Earners");

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Earners");
            uprs.moveToInsertRow();
            uprs.updateString("earnerID", earnerID);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Earner added successfully with id: " + earnerID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Earners table: ");
        printTable("Earners");
    }

    /**
     * @Insert Insert a new Song Genre in the database.
     * @throws SQLException
     */
    public static void insertSongGenre() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Song Genres are: ");
        printTable("SongGenres");

        // Genre details
        String songGenreName;
        while (true){
            System.out.print("Enter a new song genre: ");
            songGenreName = scanner.nextLine();

            if (doesRecordExists("SongGenres", songGenreName)) {
                System.out.println("Song Genre already exists with name: " + songGenreName + ", please enter new.");
            } else {
                insertSongGenre(songGenreName);
                break;
            }
        }
    }

    /**
     * @param songGenreName
     * @Insert Insert a new Song Genre with "songGenreName" in the database.
     * @throws SQLException
     */
    public static void insertSongGenre(String songGenreName) throws SQLException{
        System.out.println("\nExisting Song Genres are: ");
        printTable("SongGenres");

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM SongGenres");
            uprs.moveToInsertRow();
            uprs.updateString("songGenreName", songGenreName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("song Genre Name added successfully with id: " + songGenreName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Song Genres table: ");
        printTable("SongGenres");
    }

    /**
     * @Insert Insert a new Record Label in the database.
     * @throws SQLException
     */
    public static void insertRecordLabel() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Record labels are: ");
        printTable("RecordLabels");

        System.out.print("Enter the new Record Label details \n");
        String earnerID;

        while (true){
            System.out.print("Enter the record label Id: ");
            earnerID = scanner.nextLine();

            if (doesRecordExists("Earners", earnerID)) {
                System.out.println("Record Label already exists with id: " + earnerID + ", please enter new.");
            } else {
                insertEarner(earnerID);
                break;
            }
        }


        System.out.print("Enter the record label name: ");
        String recordLabelName = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM RecordLabels");
            uprs.moveToInsertRow();
            uprs.updateString("earnerID", earnerID);
            uprs.updateString("recordLabelName", recordLabelName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("RecordLabel added successfully with earner id: " + earnerID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter Insertion, Record labels are: ");
        printTable("RecordLabels");
    }

    /**
     * @param earnerID
     * @Insert Insert a new Record Label with "earnerID" in the database.
     * @throws SQLException
     */
    public static void insertRecordLabel(String earnerID) throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Record labels are: ");
        printTable("RecordLabels");

        if (!doesRecordExists("Earners", earnerID)) insertEarner(earnerID);

        System.out.print("Enter the record label name: ");
        String recordLabelName = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM RecordLabels");
            uprs.moveToInsertRow();
            uprs.updateString("earnerID", earnerID);
            uprs.updateString("recordLabelName", recordLabelName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("RecordLabel added successfully with earner id: " + earnerID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter Insertion, Record labels are: ");
        printTable("RecordLabels");
    }

    /**
     * @Insert Insert a new Album in the database.
     * @throws SQLException
     */
    public static void insertAlbum() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Albums are: ");
        printTable("Albums");

        String albumID;
        while (true){
            System.out.print("Enter the Album Id: ");
            albumID = scanner.nextLine();

            if (doesRecordExists("Albums", albumID)) {
                System.out.println("Album already exists with name: " + albumID + ", please enter new.");
            } else {
                insertAlbum(albumID);
                break;
            }
        }

        int albumTrackNumbers;

        while(true) {
            try {
                System.out.print("Enter the Album track Number: ");
                albumTrackNumbers = Integer.parseInt(scanner.next());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid Album track Number (numerical)");
            }
        }
        scanner.nextLine();
    }

    /**
     * @param albumID
     * @Insert Insert a new Album with "albumID" in the database.
     * @throws SQLException
     */
    public static void insertAlbum(String albumID) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Albums are: ");
        printTable("Albums");

        System.out.println("Enter the Album details related to album Id: " + albumID + "\n");
        System.out.print("Enter the Album Name: ");
        String albumName = scanner.nextLine();
        System.out.print("Enter the Album Edition (special, limited, collector’s edition): ");
        String albumEdition = scanner.nextLine();

        int albumTrackNumbers;
        while(true) {
            try {
                System.out.print("Enter the Album track Number: ");
                albumTrackNumbers = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid album track number (numerical)");
            }
        }

        System.out.print("Enter the Album release year: ");
        String albumReleaseYear = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Albums");
            uprs.moveToInsertRow();
            uprs.updateString("albumID", albumID);
            uprs.updateString("albumName", albumName);
            uprs.updateString("albumEdition", albumEdition);
            uprs.updateInt("albumTrackNumbers", albumTrackNumbers);
            uprs.updateString("albumReleaseYear", albumReleaseYear);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Album added successfully with id: " + albumID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter Insertion, Albums are: ");
        printTable("Albums");
    }

    /**
     * @Insert Insert a new Artist in the database.
     * @throws SQLException
     */
    public static void insertArtist() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Artists are: ");
        printTable("Artists");


        String artistID;
        while (true){
            System.out.print("Enter the Artists Id: ");
            artistID = scanner.nextLine();

            if (doesRecordExists("Artists", artistID)) {
                System.out.println("Artist already exists with id: " + artistID + ", please enter new.");
            } else {
                insertArtist(artistID);
                break;
            }
        }
    }

    /**
     * @param artistID
     * @Insert Insert a new Artist with "artistID" in the database.
     * @throws SQLException
     */
    public static void insertArtist(String artistID) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Artists are: ");
        printTable("Artists");

        // Record Label details
        System.out.println("\nExisting Record Labels are: ");
        printTable("RecordLabels");
        System.out.print("Enter the record label earner's Id: ");
        String recordLabelEarnerID = scanner.nextLine();
        if(!doesRecordExists("RecordLabels", recordLabelEarnerID)) insertRecordLabel(recordLabelEarnerID);

        System.out.println("\nExisting Genres are: ");
        printTable("SongGenres");
        System.out.print("Enter the artist primary genre: ");
        String artistPrimaryGenre = scanner.nextLine();
        if(!doesRecordExists("SongGenres", artistPrimaryGenre)) insertSongGenre(artistPrimaryGenre);

        System.out.println("Enter the Artists details related to artist Id: " + artistID + "\n");
        System.out.print("Enter the artist Name: ");
        String artistName = scanner.nextLine();
        System.out.print("Enter the artist active status (active/retired): ");
        String artistStatusIsActive = scanner.nextLine();

        int artistMonthlyListeners;
        while(true) {
            try {
                System.out.print("Enter the artist monthly listeners: ");
                artistMonthlyListeners = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid artist monthly listeners (numerical)");
            }
        }

        System.out.print("Enter the artist type (Band/musician/composer): ");
        String artistType = scanner.nextLine();
        System.out.print("Enter the artist country: ");
        String artistCountry = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Artists");
            uprs.moveToInsertRow();
            uprs.updateString("artistID", artistID);
            uprs.updateString("recordLabelEarnerID", recordLabelEarnerID);
            uprs.updateString("artistName", artistName);
            uprs.updateInt("artistMonthlyListeners", artistMonthlyListeners);
            uprs.updateString("artistStatusIsActive", artistStatusIsActive);
            uprs.updateString("artistPrimaryGenre", artistPrimaryGenre);
            uprs.updateString("artistType", artistType);
            uprs.updateString("artistCountry", artistCountry);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Artist added successfully with id: " + artistID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter Insertion, Artists are: ");
        printTable("Artists");
    }

    /**
     * @Insert Insert a new Song in the database.
     * @throws SQLException
     */
    public static void insertSong() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPrinting songs in database: ");
        printTable("Songs");

        // SongID details
        String songID;
        while (true){
            System.out.print("Enter the song ID: ");
            songID = scanner.nextLine();

            if (doesRecordExists("Songs", songID)) {
                System.out.println("Song already exists with ID: " + songID + ", please enter new.");
            } else {
                insertSong(songID);
                break;
            }
        }
    }

    public static void insertSong(String songID) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPrinting songs in database: ");
        printTable("Songs");


        System.out.println("\nExisting Albums in database: ");
        printTable("Albums");
        System.out.print("Enter the album Id: ");
        String albumID = scanner.nextLine();
        if(!doesRecordExists("Albums", albumID)) insertAlbum(albumID);


        System.out.println("\nExisting Artists in database: ");
        printTable("Artists");
        System.out.print("Enter the Artist Id: ");
        String artistID = scanner.nextLine();
        if(!doesRecordExists("Artists", artistID)) insertArtist(artistID);

        System.out.println("\nExisting Song Genres in database: ");
        printTable("SongGenres");
        System.out.print("Enter the song genre: ");
        String songGenreName = scanner.nextLine();
        if (!doesRecordExists("SongGenres", songGenreName)) insertSongGenre(songGenreName);

        System.out.print("Enter the song title: ");
        String songTitle = scanner.nextLine();

        double songRoyaltyRatePerPlay;
        while(true) {
            try {
                System.out.print("Enter the song royalty rate: ");
                songRoyaltyRatePerPlay = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid royalty rate (numerical)");
            }
        }

        System.out.print("Enter the song release date (YYYY-MM-DD): ");
        String songReleaseDate = scanner.nextLine();
        System.out.print("Enter the song language: ");
        String songLanguage = scanner.nextLine();
        System.out.print("Enter the song duration (HH:MM:SS): ");
        String songDuration = scanner.nextLine();


        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Songs");
            uprs.moveToInsertRow();
            uprs.updateString("songID", songID);
            uprs.updateString("songTitle", songTitle);
            uprs.updateString("albumID", albumID);
            uprs.updateDouble("songRoyaltyRatePerPlay", songRoyaltyRatePerPlay);
            uprs.updateDate("songReleaseDate", Date.valueOf(songReleaseDate));
            uprs.updateString("songLanguage", songLanguage);
            uprs.updateTime("songDuration", Time.valueOf(songDuration));

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Song added successfully with id: " + songID + " in the database.");
        System.out.println("****************************************************************\n");

        // Collaborated Artist (if present)
        System.out.print("Is there a collaborator artist in this song: " + songID + " ? (Yes or No): ");
        String isCollaborator = scanner.nextLine();
        if(isCollaborator.equalsIgnoreCase("yes")){
            int number;
            while(true) {
                try {
                    System.out.print("Enter the number of collaborators: ");
                    number = Integer.parseInt(scanner.nextLine());
                    break;
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number of collaborators (numerical)");
                }
            }

            while (number-- > 0){
                printTable("Artists");
                System.out.println("Enter the collaborator artist's Id: ");
                String artistIDCollaborated = scanner.nextLine();

                if(!doesRecordExists("Artists", artistIDCollaborated)) insertArtist(artistIDCollaborated);

                try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    ResultSet uprs = stmt.executeQuery("SELECT * FROM Collaborates");
                    uprs.moveToInsertRow();
                    uprs.updateString("artistIDMain", artistID);
                    uprs.updateString("artistIDCollaborated", artistIDCollaborated);
                    uprs.updateString("songID", songID);

                    uprs.insertRow();
                    uprs.beforeFirst();

                } catch (SQLException sqlException){
                    System.out.println("Error: " + sqlException.getMessage());
                    throw new SQLException("Error in Insertion SQL statement.");
                }
            }
        }

        // Insertion into "Sings" table
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Sings");
            uprs.moveToInsertRow();
            uprs.updateString("artistID", artistID);
            uprs.updateString("songID", songID);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }

        // Insertion into "SongBelongsTo" table
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM SongBelongsTo");
            uprs.moveToInsertRow();
            uprs.updateString("songID", songID);
            uprs.updateString("songGenreName", songGenreName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }

        System.out.println("\nAfter insertion, Songs in database : ");
        printTable("Songs");
    }

    /**
     * @Insert Insert a new User in the database;
     * @throws SQLException
     */
    public static void insertUser() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Existing Users are: \n");
        printTable("Users");

        // SongID details
        String userID;
        while (true){
            System.out.print("Enter a new user id: ");
            userID = scanner.nextLine();

            if (doesRecordExists("Users", userID)) {
                System.out.println("User already exists with ID: " + userID + ", please enter new.");
            } else {
                insertUser(userID);
                break;
            }
        }
    }

    public static void insertUser(String userID) throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Existing Users are: \n");
        printTable("Users");

        System.out.print("Enter a user first name: ");
        String userFirstName = scanner.nextLine();
        System.out.print("Enter a user last name: ");
        String userLastName = scanner.nextLine();
        System.out.print("Enter a subscription active status (active or closed): ");
        String subscriptionIsActiveStatus = scanner.nextLine();


        double monthlySubscriptionFee;
        while(true) {
            try {
                System.out.print("Enter a monthly subscription fee: ");
                monthlySubscriptionFee = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid subscription fee (numerical)");
            }
        }


        String userEmail;

        while(true)

        {
            System.out.print("Enter a user email: ");
            userEmail = scanner.nextLine();
            if (!validateEmail(userEmail)){
                System.out.println("Entered email does not match the format. Enter again");
            }
            else{

                break;
            }

        }
        String registrationDate;

        System.out.print("Enter a registration date (YYYY-MM-DD): ");
        registrationDate = scanner.nextLine();



        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Users");
            uprs.moveToInsertRow();
            uprs.updateString("userID", userID);
            uprs.updateString("userFirstName", userFirstName);
            uprs.updateString("userLastName", userLastName);
            uprs.updateString("subscriptionIsActiveStatus", subscriptionIsActiveStatus);
            uprs.updateDouble("monthlySubscriptionFee", monthlySubscriptionFee);
            uprs.updateString("userEmail", userEmail);
            uprs.updateString("registrationDate", registrationDate);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("****************************************************************");
        System.out.println("User added successfully with id: " + userID + " in the database.");
        System.out.println("****************************************************************");

        System.out.println("After Insertion, Users are: \n");
        printTable("Users");
    }

    public static void insertPodcastHost() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcast Hosts are: ");
        printTable("PodcastHosts");

        // Podcast Host details
        String earnerID;
        while (true){
            System.out.print("Enter the podcast host ID: ");
            earnerID = scanner.nextLine();

            if (doesRecordExists("PodcastHosts", earnerID)) {
                System.out.println("Podcast Host already exists with ID: " + earnerID + ", please enter new.");
            } else {
                insertPodcastHost(earnerID);
                break;
            }
        }
    }

    public static void insertPodcastHost(String earnerID) throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting PodcastHosts are: ");
        printTable("PodcastHosts");

        if (!doesRecordExists("Earners", earnerID)) insertEarner(earnerID);

        System.out.print("Enter the podcast host first name: ");
        String podcastHostFirstName = scanner.nextLine();
        System.out.print("Enter the podcast host last name: ");
        String podcastHostLastName = scanner.nextLine();

        String podcastHostEmail;
        while(true)

        {
            System.out.print("Enter the podcast host email: ");
            podcastHostEmail = scanner.nextLine();
            if (!validateEmail(podcastHostEmail)){
                System.out.println("Entered email does not match the format. Enter again");
            }
            else{

                break;
            }

        }
        String podcastHostPhone;
        while(true)

        {
            System.out.print("Enter the podcast host phone (10 characters): ");
            podcastHostPhone = scanner.nextLine();
            if (!validatePhoneNumber(podcastHostPhone)){
                System.out.println("Entered phone number does not match the format. Enter again");
            }
            else{

                break;
            }

        }




        double flatFee;
        while(true) {
            try {
                System.out.print("Enter the podcast host flatFee: ");
                flatFee = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid flatFee for podcast host: (numerical)");
            }
        }

        double adBonus;
        while(true) {
            try {
                System.out.print("Enter the podcast host adBonus: ");
                adBonus = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid adBonus for podcast host: (numerical)");
            }
        }

        System.out.print("Enter the podcast host city: ");
        String podcastHostCity = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastHosts");
            uprs.moveToInsertRow();
            uprs.updateString("podcastHostEarnerId", earnerID);
            uprs.updateString("podcastHostFirstName", podcastHostFirstName);
            uprs.updateString("podcastHostLastName", podcastHostLastName);
            uprs.updateString("podcastHostEmail", podcastHostEmail);
            uprs.updateString("podcastHostPhone", podcastHostPhone);
            uprs.updateString("podcastHostCity", podcastHostCity);
            uprs.updateDouble("flatFee", flatFee);
            uprs.updateDouble("adBonus", adBonus);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("PodcastHost added successfully with id: " + earnerID+ " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter Insertion, PodcastHosts are: ");
        printTable("PodcastHosts");
    }

    /**
     * @Insert Insert a new Podcast Genre in the database.
     * @throws SQLException
     */
    public static void insertPodcastGenre() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcast Genres are: ");
        printTable("PodcastGenres");

        // Genre details
        String podcastGenreName;
        while (true){
            System.out.print("Enter a new Podcast genre: ");
            podcastGenreName = scanner.nextLine();

            if (doesRecordExists("PodcastGenres", podcastGenreName)) {
                System.out.println("Podcast Genre already exists with name: " + podcastGenreName + ", please enter new.");
            } else {
                insertPodcastGenre(podcastGenreName);
                break;
            }
        }
    }

    /**
     * @param podcastGenreName
     * @Insert Insert a new Podcast Genre with "podcastGenreName" in the database.
     * @throws SQLException
     */
    public static void insertPodcastGenre(String podcastGenreName) throws SQLException{
        System.out.println("\nExisting Podcast Genres are: ");
        printTable("PodcastGenres");

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastGenres");
            uprs.moveToInsertRow();
            uprs.updateString("podcastGenreName", podcastGenreName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Podcast Genre Name added successfully with id: " + podcastGenreName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Podcast Genres table: ");
        printTable("PodcastGenres");
    }

    /**
     * @Insert Insert a new Special Guest in the database.
     * @throws SQLException
     */
    public static void insertSpecialGuests() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Special Guest table is: ");
        printTable("SpecialGuests");

        System.out.print("Enter a new Guest name: ");
        String guestName = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM SpecialGuests");
            uprs.moveToInsertRow();
            uprs.updateString("guestName", guestName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Guest Name added successfully with id: " + guestName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Special Guest table: ");
        printTable("SpecialGuests");
    }

    /**
     * @param guestName
     * @Insert Insert a new Special Guests in the database.
     * @throws SQLException
     */
    public static void insertSpecialGuests(String guestName) throws SQLException{
        System.out.println("\nExisting Special Guest table is: ");
        printTable("SpecialGuests");

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM SpecialGuests");
            uprs.moveToInsertRow();
            uprs.updateString("guestName", guestName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Guest Name added successfully with id: " + guestName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Special Guest table: ");
        printTable("SpecialGuests");
    }

    /**
     * @Insert Insert a new Podcast sponsor in the database.
     * @throws SQLException
     */
    public static void insertPodcastSponsor() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcast sponsors are: ");
        printTable("PodcastSponsors");

        System.out.print("Enter a new Podcast sponsor name: ");
        String podcastSponsorName = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastSponsors");
            uprs.moveToInsertRow();
            uprs.updateString("podcastSponsorName", podcastSponsorName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Podcast Sponsor added successfully with id: " + podcastSponsorName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Podcast Sponsor table: ");
        printTable("PodcastSponsors");
    }

    /**
     * @param podcastSponsorName
     * @Insert Insert a new Podcast sponsor in the database.
     * @throws SQLException
     */
    public static void insertPodcastSponsor(String podcastSponsorName) throws SQLException{
        System.out.println("\nExisting Podcast sponsors are: ");
        printTable("PodcastSponsors");

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastSponsors");
            uprs.moveToInsertRow();
            uprs.updateString("podcastSponsorName", podcastSponsorName);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Podcast Sponsor added successfully with id: " + podcastSponsorName + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("\nAfter insertion, Podcast Sponsor table: ");
        printTable("PodcastSponsors");
    }

    /**
     * @Insert Insert a new Podcast in the database.
     * @throws SQLException
     */
    public static void insertPodcast() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcasts are: ");
        printTable("Podcasts");

        // Genre details
        String podcastID;
        while (true){
            System.out.print("Enter the podcast ID: ");
            podcastID = scanner.nextLine();

            if (doesRecordExists("Podcasts", podcastID)) {
                System.out.println("Podcast already exists with name: " + podcastID + ", please enter new.");
            } else {
                insertPodcast(podcastID);
                break;
            }
        }
    }

    /**
     * @param podcastID
     * @Insert Insert a new Podcast with "podcastID" in the database.
     * @throws SQLException
     */
    public static void insertPodcast(String podcastID) throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcasts are: ");
        printTable("Podcasts");


        System.out.println("Enter the Podcast Host ID: ");
        String podcastHostEarnerId = scanner.nextLine();
        if (!doesRecordExists("PodcastHosts", podcastHostEarnerId)) insertPodcastHost(podcastHostEarnerId);


        System.out.println("Enter the Podcast Genre: ");
        String podcastGenreName = scanner.nextLine();
        if (!doesRecordExists("PodcastGenres", podcastGenreName)) insertPodcastGenre(podcastGenreName);

        System.out.print("Enter the podcast name: ");
        String podcastName = scanner.nextLine();

        int podcastEpisodeCount;
        while(true) {
            try {
                System.out.print("Enter the podcast episode count: ");
                podcastEpisodeCount = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid podcast episode count (numerical)");
            }
        }

        double flatFeePerEpisode;
        while(true) {
            try {
                System.out.print("Enter the podcast flat fee per episode: ");
                flatFeePerEpisode = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid podcast flat fee per episode (numerical)");
            }
        }

        double podcastRating;
        while(true) {
            try {
                System.out.print("Enter the podcast rating: ");
                podcastRating = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid podcast rating: (numerical)");
            }
        }



        int podcastTotalSubscribers;
        while(true) {
            try {
                System.out.print("Enter the podcast total subscribers: ");
                podcastTotalSubscribers = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid podcast total subscribers (numerical)");
            }
        }

        System.out.print("Enter the podcast language: ");
        String podcastLanguage = scanner.nextLine();
        System.out.print("Enter the podcast country: ");
        String podcastCountry = scanner.nextLine();

        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Podcasts");
            uprs.moveToInsertRow();
            uprs.updateString("podcastId", podcastID);
            uprs.updateString("podcastName", podcastName);
            uprs.updateInt("podcastEpisodeCount", podcastEpisodeCount);
            uprs.updateDouble("flatFeePerEpisode", flatFeePerEpisode);
            uprs.updateDouble("podcastRating", podcastRating);
            uprs.updateInt("podcastTotalSubscribers", podcastTotalSubscribers);
            uprs.updateString("podcastLanguage", podcastLanguage);
            uprs.updateString("podcastCountry", podcastCountry);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }


        // Insertion in the "PodcastSponsoredBy" table
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastBelongsTo");
            uprs.moveToInsertRow();
            uprs.updateString("podcastGenreName", podcastGenreName);
            uprs.updateString("podcastId", podcastID);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }

        // Insertion in the runs table
        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM Runs");
            uprs.moveToInsertRow();
            uprs.updateString("podcastHostEarnerId", podcastHostEarnerId);
            uprs.updateString("podcastId", podcastID);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }


        System.out.println("\n****************************************************************");
        System.out.println("Podcast added successfully with podcast id: " + podcastID + " in the database.");
        System.out.println("****************************************************************\n");

        System.out.println("Does the Podcast have any sponsors (Yes or No): ");
        String isSponsored = scanner.nextLine();
        if (isSponsored.equalsIgnoreCase("yes")) {
            int number;
            while(true) {
                try {
                    System.out.print("Enter the number of sponsors: ");
                    number = Integer.parseInt(scanner.nextLine());
                    break;
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number of sponsors (numerical)");
                }
            }


            while (number-- > 0) {
                printTable("PodcastSponsors");
                System.out.println("Enter Sponsor Name: ");
                String podcastSponsorName = scanner.nextLine();
                if (!doesRecordExists("PodcastSponsors", podcastSponsorName)) insertPodcastSponsor(podcastSponsorName);

                // Insertion in the "PodcastSponsoredBy" table
                try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastSponsoredBy");
                    uprs.moveToInsertRow();
                    uprs.updateString("podcastSponsorName", podcastSponsorName);
                    uprs.updateString("podcastId", podcastID);

                    uprs.insertRow();
                    uprs.beforeFirst();

                } catch (SQLException sqlException) {
                    System.out.println("Error: " + sqlException.getMessage());
                    throw new SQLException("Error in Insertion SQL statement.");
                }
            }
        }

        System.out.println("\nAfter Insertion, Podcast are: ");
        printTable("Podcasts");
    }

    public static void insertPodcastEpisode() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcast Episodes are: ");
        printTable("PodcastEpisodes");

        // Podcast Episodes details
        String podcastEpisodeId;
        while (true){
            System.out.print("Enter a new Podcast Episode ID: ");
            podcastEpisodeId = scanner.nextLine();

            if (doesRecordExists("PodcastEpisodes", podcastEpisodeId)) {
                System.out.println("Podcast Episode already exists with ID: " + podcastEpisodeId + ", please enter new.");
            } else {
                insertPodcastEpisode(podcastEpisodeId);
                break;
            }
        }
    }

    public static void insertPodcastEpisode(String podcastEpisodeId) throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nExisting Podcast Episodes are: ");
        printTable("PodcastEpisodes");

        System.out.print("Enter the new Podcast Episode details \n");
        System.out.print("Enter the podcast ID: ");
        String podcastID = scanner.nextLine();
        if (!doesRecordExists("Podcasts", podcastID)) insertPodcast(podcastID);


        System.out.print("Enter the podcast episode title: ");
        String podcastEpisodeTitle = scanner.nextLine();
        System.out.print("Enter the podcast episode duration (HH:MM:SS): ");
        String podcastEpisodeDuration = scanner.nextLine();
        System.out.print("Enter the podcast episode release date (YYYY-MM-DD): ");
        String podcastEpisodeReleaseDate = scanner.nextLine();


        try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet uprs = stmt.executeQuery("SELECT * FROM PodcastEpisodes");
            uprs.moveToInsertRow();
            uprs.updateString("podcastEpisodeId", podcastEpisodeId);
            uprs.updateString("podcastEpisodeTitle", podcastEpisodeTitle);
            uprs.updateString("podcastId", podcastID);
            uprs.updateString("podcastEpisodeDuration", podcastEpisodeDuration);
            uprs.updateString("podcastEpisodeReleaseDate", podcastEpisodeReleaseDate);

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            throw new SQLException("Error in Insertion SQL statement.");
        }
        System.out.println("\n****************************************************************");
        System.out.println("Podcast Episode added successfully with id: " + podcastEpisodeId+ " in the database.");
        System.out.println("****************************************************************\n");

        //Guest Info, Insertion in Features tables
        System.out.println("Does the Podcast Episode have any Special Guests (Yes or No): ");
        String isSpecialGuest = scanner.nextLine();
        if (isSpecialGuest.equalsIgnoreCase("yes")){
            int number;
            while(true) {
                try {
                    System.out.print("Enter the number of guests: ");
                    number = Integer.parseInt(scanner.nextLine());
                    break;
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number of guests (numerical)");
                }
            }

            while (number-- > 0) {
                printTable("SpecialGuests");
                System.out.println("Enter Special Guest Name: ");
                String guestName = scanner.nextLine();
                if (!doesRecordExists("SpecialGuests", guestName)) insertSpecialGuests(guestName);

        // Insertion in the "Features" table
                try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    ResultSet uprs = stmt.executeQuery("SELECT * FROM Features");
                    uprs.moveToInsertRow();
                    uprs.updateString("guestName", guestName);
                    uprs.updateString("podcastEpisodeId", podcastEpisodeId);

                    uprs.insertRow();
                    uprs.beforeFirst();

                } catch (SQLException sqlException) {
                    System.out.println("Error: " + sqlException.getMessage());
                    throw new SQLException("Error in Insertion SQL statement.");
                }
            }
        }

        System.out.println("\nAfter Insertion, Podcast Episodes are: ");
        printTable("PodcastEpisodes");
    }

    public static void updateMenu() throws SQLException {

        System.out.println("\n Update Menu:");
        System.out.println("1. Update in Song");
        System.out.println("2. Update in Artist");
        System.out.println("3. Update in Album");
        System.out.println("4. Update in Podcast Host");
        System.out.println("5. Update in Podcast");
        System.out.println("6. Update in Podcast Episode");

        Scanner scanner = new Scanner(System.in);
        int updateChoice;
        while(true) {
            try {
                System.out.print("Enter a choice: ");
                updateChoice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }


        // Perform the selected action
        switch (updateChoice) {
            case 1 -> updateSong();
            case 2 -> updateArtist();
            case 3 -> updateAlbum();
            case 4 -> updatePodcastHost();
            case 5 -> updatePodcast();
            case 6 -> updatePodcastEpisode();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void updateSong() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Song Title");
        System.out.println("2. Song Play Count");
        System.out.println("3. Song Royalty Rate");
        System.out.println("4. Song Royalty Paid Status");
        System.out.println("5. Song Release Date");
        System.out.println("6. Song Language");
        System.out.println("7. Song Duration");

        int updateSong;
        while(true) {
            try {
                System.out.print("Enter a choice: ");
                updateSong = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String songId;
        while (true){
            System.out.print("Enter the songID to be updated");
            songId = scanner.nextLine();

            if (!doesRecordExists("Songs", songId)) {
                System.out.println("Song ID does not exists\nThe available song IDs are:");

                String sQuery = "SELECT songID FROM Songs;";
                ResultSet rs = statement.executeQuery(sQuery);
                while (rs.next())
                {
                    String sId = rs.getString("songID");
                    // print the results
                    System.out.format("%s\n", sId);
                }
                System.out.println();

            }
            else {
                break;
            }
        }

        if (doesRecordExists("Songs", songId)) {

            //update the given actions

            switch (updateSong) {
                case 1 -> {
                    System.out.print("Enter the new Song Title: ");
                    String updatedSongTitle = scanner.nextLine();
                    statement.executeUpdate("UPDATE Songs SET songTitle = \'" + updatedSongTitle + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song Title updated successfully.");
                }
                case 2 -> {
                    int updatedSongPlayCount;
                    while(true) {
                        try {
                            System.out.print("Enter the new Song Play Count: ");
                            updatedSongPlayCount = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Song Play Count (numerical)");
                        }
                    }



                    statement.executeUpdate("UPDATE Songs SET playCountCurrentMonth = \'" + updatedSongPlayCount + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song Play Count updated successfully.");
                }
                case 3 -> {
                    double updatedSongRoyaltyRate;
                    while(true) {
                        try {
                            System.out.print("Enter the new Song Royalty Rate: ");
                            updatedSongRoyaltyRate = Double.parseDouble(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid royalty rate (numerical double)");
                        }
                    }

                    statement.executeUpdate("UPDATE Songs SET songRoyaltyRatePerPlay = \'" + updatedSongRoyaltyRate + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song Royalty Rate updated successfully.");
                }
                case 4 -> {
                    System.out.print("Enter the new Song Royalty paid status (yes/no): ");
                    String updatedSongRoyaltyPaidStatus = scanner.nextLine();
                    statement.executeUpdate("UPDATE Songs SET isSongRoyaltyPaid = \'" + updatedSongRoyaltyPaidStatus + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song Royalty Paid Status updated successfully.");
                }
                case 5 -> {
                    System.out.print("Enter the new song Release Date in format('YYYY-MM-DD'): ");
                    String updateSongReleaseDate = scanner.nextLine();
                    statement.executeUpdate("UPDATE Songs SET songReleaseDate = \'" + updateSongReleaseDate + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song released date updated successfully.");
                }
                case 6 -> {
                    System.out.print("Enter the new song language: ");
                    String updateSongLanguage = scanner.nextLine();
                    statement.executeUpdate("UPDATE Songs SET songLanguage = \'" + updateSongLanguage + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song language updated successfully.");
                }
                case 7 -> {
                    System.out.print("Enter the new song Duration in format('HH:MM:SS'): ");
                    String updatedSongDuration = scanner.nextLine();
                    statement.executeUpdate("UPDATE Songs SET songDuration = \'" + updatedSongDuration + "\' WHERE songID = \'" + songId + "\';");
                    System.out.println("Song duration updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showSongInfo(songId);
        }
        else{
            System.out.println("Song ID does not exists\nThe available song IDs are:");

            String sQuery = "SELECT songID FROM Songs;";
            ResultSet rs = statement.executeQuery(sQuery);
            while (rs.next())
            {
                String sId = rs.getString("songID");
                // print the results
                System.out.format("%s\n", sId);
            }
            System.out.println();

        }
    }

    public static void updateArtist() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Artist Name");
        System.out.println("2. Artist Status");
        System.out.println("3. Artist Monthly Listeners");
        System.out.println("4. Artist Primary Genre");
        System.out.println("5. Artist Type");
        System.out.println("6. Artist country");

        int updateArtist;
        while(true) {
            try {
                System.out.print("Enter your choice");
                updateArtist = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String artistId;
        while (true){
            System.out.print("\nEnter the ArtistID to be updated");
            artistId = scanner.nextLine();

            if (!doesRecordExists("Artists", artistId)) {
                System.out.println("Artist ID does not exist\nThe available Artist IDs are:");

                String sQuery = "SELECT artistID FROM Artists;";
                ResultSet rs = statement.executeQuery(sQuery);
                while (rs.next())
                {
                    String arId = rs.getString("artistID");
                    // print the results
                    System.out.format("%s\n", arId);
                }
                System.out.println();
            }
            else {
                break;
            }
        }

        //update the given actions

        if (doesRecordExists("Artists", artistId)) {
            switch (updateArtist) {
                case 1 -> {
                    System.out.print("Enter the new Artist Name: ");
                    String updatedArtistName = scanner.nextLine();
                    statement.executeUpdate("UPDATE Artists SET artistName = \'" + updatedArtistName + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist Name updated successfully.");
                }
                case 2 -> {
                    System.out.print("Enter the new Artist Status: ");
                    String updatedArtistStatus = scanner.nextLine();
                    statement.executeUpdate("UPDATE Artists SET artistStatusIsActive = \'" + updatedArtistStatus + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist Status updated successfully.");
                }
                case 3 -> {
                    int updatedArtistMonthlyListeners;
                    while(true) {
                        try {
                            System.out.print("Enter the new Artist Monthly Listeners: ");
                            updatedArtistMonthlyListeners = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Artist Monthly Listeners count (numerical)");
                        }
                    }

                    statement.executeUpdate("UPDATE Artists SET artistMonthlyListeners = \'" + updatedArtistMonthlyListeners + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist Monthly Listeners updated successfully.");
                }
                case 4 -> {
                    System.out.print("Enter the new Artist Primary Genre: ");
                    String updatedArtistPrimaryGenre = scanner.nextLine();
                    statement.executeUpdate("UPDATE Artists SET artistPrimaryGenre = \'" + updatedArtistPrimaryGenre + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist Primary Genre updated successfully.");
                }
                case 5 -> {
                    System.out.print("Enter the new Artist Type (Band/musician/composer): ");
                    String updatedArtistType = scanner.nextLine();
                    statement.executeUpdate("UPDATE Artists SET artistType = \'" + updatedArtistType + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist Type updated successfully.");
                }
                case 6 -> {
                    System.out.print("Enter the new Artist country: ");
                    String updatedArtistCountry = scanner.nextLine();
                    statement.executeUpdate("UPDATE Artists SET artistCountry = \'" + updatedArtistCountry + "\' WHERE artistID = \'" + artistId + "\';");
                    System.out.println("Artist country updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showArtistInfo(artistId);
        } else{
            System.out.println("Artist ID does not exists\nThe available Artist IDs are:");

            String sQuery = "SELECT artistID FROM Artists;";
            ResultSet rs = statement.executeQuery(sQuery);
            while (rs.next())
            {
                String arId = rs.getString("artistID");
                // print the results
                System.out.format("%s\n", arId);
            }
            System.out.println();
        }
    }

    public static void updateAlbum() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Album Name");
        System.out.println("2. Album Edition");
        System.out.println("3. Album Track Numbers");
        System.out.println("4. Album Release Year");


        int updateAlbum;
        while(true) {
            try {
                System.out.print("Enter your choice");
                updateAlbum = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String albumId;
        while (true){
            System.out.print("Enter the Album ID to be updated");
            albumId = scanner.nextLine();

            if (!doesRecordExists("Albums", albumId)) {
                System.out.println("Album ID does not exists\nThe available album IDs are:");

                String alQuery = "SELECT albumID FROM Albums;";
                ResultSet rs = statement.executeQuery(alQuery);
                while (rs.next())
                {
                    String alId = rs.getString("albumID");
                    // print the results
                    System.out.format("%s\n", alId);
                }
                System.out.println();
            }
            else {
                break;
            }
        }

        if (doesRecordExists("Albums", albumId)) {

            //update the given actions

            switch (updateAlbum) {
                case 1 -> {
                    System.out.print("Enter the new Album Name: ");
                    String updatedAlbumName = scanner.nextLine();
                    statement.executeUpdate("UPDATE Albums SET albumName = \'" + updatedAlbumName + "\' WHERE albumID = \'" + albumId + "\';");
                    System.out.println("Album Name updated successfully.");
                }
                case 2 -> {
                    System.out.print("Enter the new Album Edition: ");
                    String updatedAlbumEdition = scanner.nextLine();
                    statement.executeUpdate("UPDATE Albums SET albumEdition = \'" + updatedAlbumEdition + "\' WHERE albumID = \'" + albumId + "\';");
                    System.out.println("Album Edition updated successfully.");
                }
                case 3 -> {
                    int updatedAlbumTrackNumbers;
                    while(true) {
                        try {
                            System.out.print("Enter the new Album Track Numbers: ");
                            updatedAlbumTrackNumbers = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid choice (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE Albums SET albumTrackNumbers = \'" + updatedAlbumTrackNumbers + "\' WHERE albumID = \'" + albumId + "\';");
                    System.out.println("Album Track numbers updated successfully.");
                }
                case 4 -> {
                    System.out.print("Enter the new Album Release Year: ");
                    String updatedAlbumReleaseYear = scanner.nextLine();
                    statement.executeUpdate("UPDATE Albums SET albumReleaseYear = \'" + updatedAlbumReleaseYear + "\' WHERE albumID = \'" + albumId + "\';");
                    System.out.println("Album Track numbers updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showAlbumInfo(albumId);
        } else{
            System.out.println("Album ID does not exists\nThe available album IDs are:");

            String alQuery = "SELECT albumID FROM Albums;";
            ResultSet rs = statement.executeQuery(alQuery);
            while (rs.next())
            {
                String alId = rs.getString("albumID");
                // print the results
                System.out.format("%s\n", alId);
            }
            System.out.println();

        }
    }

    public static void updatePodcastHost() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Podcast Host First Name");
        System.out.println("2. Podcast Host Last Name");
        System.out.println("3. Podcast Host Email");
        System.out.println("4. Podcast Host Phone Number");
        System.out.println("5. Podcast Host City");
        System.out.println("6. Podcast Host Flat Fee");
        System.out.println("7. Podcast Host Ad Bonus");

        int updatePodcastHost;
        while(true) {
            try {
                System.out.print("Enter your choice");
                updatePodcastHost = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String podcastHostId;
        while (true){
            System.out.print("Enter the Podcast Host ID to be updated");
            podcastHostId = scanner.nextLine();

            if (!doesRecordExists("PodcastHosts", podcastHostId)) {
                System.out.println("Podcast Host ID does not exists\nThe available podcast Host IDs are:");

                String phQuery = "SELECT podcastHostEarnerId FROM PodcastHosts;";
                ResultSet rs = statement.executeQuery(phQuery);
                while (rs.next())
                {
                    String phId = rs.getString("podcastHostEarnerId");
                    // print the results
                    System.out.format("%s\n", phId);
                }
                System.out.println();
            }
            else {
                break;
            }
        }

        if (doesRecordExists("PodcastHosts", podcastHostId)) {

            //update the given actions

            switch (updatePodcastHost) {
                case 1 -> {
                    System.out.print("Enter the new Podcast Host First Name: ");
                    String updatedPodcastHostFName = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastHosts SET podcastHostFirstName = \'" + updatedPodcastHostFName + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                    System.out.println("Podcast Host First Name updated successfully.");
                }
                case 2 -> {
                    System.out.print("Enter the new Podcast Host Last Name: ");
                    String updatedPodcastHostLName = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastHosts SET podcastHostLastName = \'" + updatedPodcastHostLName + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                    System.out.println("Podcast Host Last Name updated successfully.");
                }
                case 3 -> {
                    System.out.print("Enter the new Podcast Host Email: ");
                    String updatedPodcastHostEmail = scanner.nextLine();
                    if (validateEmail(updatedPodcastHostEmail)) {

                        statement.executeUpdate("UPDATE PodcastHosts SET podcastHostEmail = \'" + updatedPodcastHostEmail + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                        System.out.println("Podcast Host Email updated successfully.");
                    } else {
                        System.out.println("Entered email does not match the format");

                    }
                }
                case 4 -> {
                    System.out.print("Enter the new Podcast Host Phone: ");
                    String updatedPodcastHostPhone = scanner.nextLine();
                    if (validatePhoneNumber(updatedPodcastHostPhone)) {
                        statement.executeUpdate("UPDATE PodcastHosts SET podcastHostPhone = \'" + updatedPodcastHostPhone + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                        System.out.println("Podcast Host Phone updated successfully.");
                    }
                    else{
                        System.out.println("Entered phone number does not match the format");
                    }
                }
                case 5 -> {
                    System.out.print("Enter the new Podcast Host City: ");
                    String updatedPodcastHostCity = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastHosts SET podcastHostCity = \'" + updatedPodcastHostCity + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                    System.out.println("Podcast Host City updated successfully.");
                }
                case 6 -> {
                    double updatedPodcastHostFee;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Host Flat Fee: ");
                            updatedPodcastHostFee = Double.parseDouble(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Podcast Host Flat Fee (numerical)");
                        }
                    }

                    statement.executeUpdate("UPDATE PodcastHosts SET flatFee = \'" + updatedPodcastHostFee + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                    System.out.println("Podcast Host Flat Fee updated successfully.");
                }
                case 7 -> {
                    double updatedPodcastHostAdBonus;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Host Ad Bonus: ");
                            updatedPodcastHostAdBonus = Double.parseDouble(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Podcast Host Ad Bonus (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE PodcastHosts SET adBonus = \'" + updatedPodcastHostAdBonus + "\' WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
                    System.out.println("Podcast Host Ad Bonus updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showPodcastHostInfo(podcastHostId);
        } else{
            System.out.println("Podcast Host ID does not exists\nThe available podcast Host IDs are:");

            String phQuery = "SELECT podcastHostEarnerId FROM PodcastHosts;";
            ResultSet rs = statement.executeQuery(phQuery);
            while (rs.next())
            {
                String phId = rs.getString("podcastHostEarnerId");
                // print the results
                System.out.format("%s\n", phId);
            }
            System.out.println();
        }
    }

    public static void updatePodcast() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Podcast Name");
        System.out.println("2. Podcast Episode Count");
        System.out.println("3. Podcast Flat Fee Per Episode");
        System.out.println("4. Podcast Rating");
        System.out.println("5. Podcast Total Subscribers");
        System.out.println("6. Podcast Language");
        System.out.println("7. Podcast Country");

        int updatePodcast;
        while(true) {
            try {
                System.out.print("Enter your choice");
                updatePodcast = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String podcastID;
        while (true){
            System.out.print("Enter the Podcast ID to be updated");
            podcastID = scanner.nextLine();

            if (!doesRecordExists("Podcasts", podcastID)) {
                System.out.println("Podcast ID does not exists\nThe available podcast IDs are:");

                String pQuery = "SELECT podcastId FROM Podcasts;";
                ResultSet rs = statement.executeQuery(pQuery);
                while (rs.next())
                {
                    String pId = rs.getString("podcastId");
                    // print the results
                    System.out.format("%s\n", pId);
                }
                System.out.println();
            }
            else {
                break;
            }
        }

        if (doesRecordExists("Podcasts", podcastID)) {

            //update the given actions

            switch (updatePodcast) {
                case 1 -> {
                    System.out.print("Enter the new Podcast Name: ");
                    String updatedPodcastName = scanner.nextLine();
                    statement.executeUpdate("UPDATE Podcasts SET podcastName = \'" + updatedPodcastName + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Name updated successfully.");
                }
                case 2 -> {
                    int updatedPodcastEpisodeCount;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Episode Count: ");
                            updatedPodcastEpisodeCount = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Podcast Episode Count (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE Podcasts SET podcastEpisodeCount = \'" + updatedPodcastEpisodeCount + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Episode Count updated successfully.");
                }
                case 3 -> {
                    double updatedPodcastFlatFeePerEpisode;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Flat Fee per Episode: ");
                            updatedPodcastFlatFeePerEpisode = Double.parseDouble(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Podcast Flat Fee per Episode (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE Podcasts SET flatFeePerEpisode = \'" + updatedPodcastFlatFeePerEpisode + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Flat Fee per Episode updated successfully.");
                }
                case 4 -> {
                    double updatedPodcastRating;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Rating: ");
                            updatedPodcastRating = Double.parseDouble(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Podcast Rating (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE Podcasts SET podcastRating = \'" + updatedPodcastRating + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Rating updated successfully.");
                }
                case 5 -> {
                    int updatedPodcastTotalSubscribers;
                    while(true) {
                        try {
                            System.out.print("Enter the new Total Subscribers for Podcast : ");
                            updatedPodcastTotalSubscribers = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid Subscribers Count (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE Podcasts SET podcastTotalSubscribers = \'" + updatedPodcastTotalSubscribers + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("The Total Subscribers for Podcast updated successfully.");
                }
                case 6 -> {
                    System.out.print("Enter the new Podcast Language: ");
                    String updatedPodcastLanguage = scanner.nextLine();
                    statement.executeUpdate("UPDATE Podcasts SET podcastLanguage = \'" + updatedPodcastLanguage + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Language updated successfully.");
                }
                case 7 -> {
                    System.out.print("Enter the new Podcast Country: ");
                    String updatedPodcastCountry = scanner.nextLine();
                    statement.executeUpdate("UPDATE Podcasts SET podcastCountry = \'" + updatedPodcastCountry + "\' WHERE podcastId = \'" + podcastID + "\';");
                    System.out.println("Podcast Country updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showPodcastInfo(podcastID);
        } else{
            System.out.println("Podcast ID does not exists\nThe available podcast IDs are:");

            String pQuery = "SELECT podcastId FROM Podcasts;";
            ResultSet rs = statement.executeQuery(pQuery);
            while (rs.next())
            {
                String pId = rs.getString("podcastId");
                // print the results
                System.out.format("%s\n", pId);
            }
            System.out.println();
        }
    }

    public static void updatePodcastEpisode() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Podcast Episode Title");
        System.out.println("2. Podcast Episode Listening Count");
        System.out.println("3. Podcast Episode Advertisement Count");
        System.out.println("4. Podcast Episode Duration");
        System.out.println("5. Podcast Episode Release Date");

        int updatePodcastEpisode;
        while(true) {
            try {
                System.out.print("Enter your choice");
                updatePodcastEpisode = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }

        String podcastEpisodetID;
        while (true){
            System.out.print("Enter the Podcast Episode ID to be updated");
            podcastEpisodetID = scanner.nextLine();

            if (!doesRecordExists("PodcastEpisodes", podcastEpisodetID)) {
                System.out.println("Podcast Episode ID does not exists\nThe available Episode IDs are:");

                String alQuery = "SELECT podcastEpisodeId FROM PodcastEpisodes;";
                ResultSet rs = statement.executeQuery(alQuery);
                while (rs.next())
                {
                    String peId = rs.getString("podcastEpisodeId");
                    // print the results
                    System.out.format("%s\n", peId);
                }
                System.out.println();
            }
            else {
                break;
            }
        }

        if (doesRecordExists("PodcastEpisodes", podcastEpisodetID)) {

            //update the given actions

            switch (updatePodcastEpisode) {
                case 1 -> {
                    System.out.print("Enter the new Podcast Episode Title: ");
                    String updatedPodcastEpisodeTitle = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastEpisodes SET podcastEpisodeTitle = \'" + updatedPodcastEpisodeTitle + "\' WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\';");
                    System.out.println("Podcast Episode Title updated successfully.");
                }
                case 2 -> {
                    int updatedPodcastEpisodeListeningCount;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Episode Listening Count: ");
                            updatedPodcastEpisodeListeningCount = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid choice (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE PodcastEpisodes SET podcastEpisodeListeningCount = \'" + updatedPodcastEpisodeListeningCount + "\' WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\';");
                    System.out.println("Podcast Episode Listening Count updated successfully.");
                }
                case 3 -> {
                    int updatedPodcastEpisodeAdvertisementCount;
                    while(true) {
                        try {
                            System.out.print("Enter the new Podcast Episode Advertisement Count: ");
                            updatedPodcastEpisodeAdvertisementCount = Integer.parseInt(scanner.nextLine());
                            break;
                        }
                        catch(Exception e) {
                            System.out.println("Please enter a valid choice (numerical)");
                        }
                    }
                    statement.executeUpdate("UPDATE PodcastEpisodes SET podcastEpisodeAdvertisementCount = \'" + updatedPodcastEpisodeAdvertisementCount + "\' WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\';");
                    System.out.println("Podcast Episode Advertisement Count updated successfully.");
                }
                case 4 -> {
                    System.out.print("Enter the new Podcast Episode Duration ('HH:MM:SS'): ");
                    String updatedPodcastEpisodeDuration = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastEpisodes SET podcastEpisodeDuration = \'" + updatedPodcastEpisodeDuration + "\' WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\';");
                    System.out.println("Podcast Episode Duration updated successfully.");
                }
                case 5 -> {
                    System.out.print("Enter the new Podcast Episode Release Date (YYYY-MM-DD): ");
                    String updatedPodcastEpisodeReleaseDate = scanner.nextLine();
                    statement.executeUpdate("UPDATE PodcastEpisodes SET podcastEpisodeReleaseDate = \'" + updatedPodcastEpisodeReleaseDate + "\' WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\';");
                    System.out.println("Podcast Episode Release Date updated successfully.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            showPodcastEpisodeInfo(podcastEpisodetID);
        } else{
            System.out.println("Podcast Episode ID does not exists\nThe available Episode IDs are:");

            String alQuery = "SELECT podcastEpisodeId FROM PodcastEpisodes;";
            ResultSet rs = statement.executeQuery(alQuery);
            while (rs.next())
            {
                String peId = rs.getString("podcastEpisodeId");
                // print the results
                System.out.format("%s\n", peId);
            }
            System.out.println();
        }
    }

    public static void deleteMenu() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Delete Menu:");
        System.out.println("1. Delete a Song Genre Entry");
        System.out.println("2. Delete a Record Label Entry");
        System.out.println("3. Delete a Album Entry");
        System.out.println("4. Delete a Artist Entry");
        System.out.println("5. Delete a Song Entry");
        System.out.println("6. Delete a Podcast Host Entry");
        System.out.println("7. Delete a Podcast episode Entry");
        System.out.println("8. Delete a Podcast Entry");
        System.out.println("9. Delete a User Entry");

        int choice;
        while(true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid choice (numerical)");
            }
        }


        // Perform the selected action
        switch (choice) {
            case 1 -> deleteSongGenre();
            case 2 -> deleteRecordLabel();
            case 3 -> deleteAlbum();
            case 4 -> deleteArtist();
            case 5 -> deleteSong();
            case 6 -> deletePodcastHost();
            case 7 -> deletePodcastEpisode();
            case 8 -> deletePodcast();
            case 9 -> deleteUser();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void deleteSongGenre() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Song Genres are: ");
        printTable("SongGenres");

        System.out.print("Enter the Song Genre name to be deleted: ");
        String songGenreName = scanner.nextLine();

        if (doesRecordExists("SongGenres", songGenreName)){
    //delete the given item

            statement.executeUpdate("DELETE FROM SongGenres WHERE songGenreName = \'" + songGenreName + "\';");
            System.out.println("Song Genre row with name " + songGenreName + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("SongGenres");
        } else {
            System.out.println("Song Genre does not exists\nThe available Song Genre are:");
            printTable("SongGenres");
        }
    }

    public static void deleteAlbum() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Albums are: ");
        printTable("Albums");

        System.out.print("Enter the Album ID of the entry to be deleted: ");
        String albumID = scanner.nextLine();

        if (doesRecordExists("Albums", albumID)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Albums WHERE albumID = \'" + albumID + "\';");
            System.out.println("Album row with ID: " + albumID + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("Albums");
        } else {
            System.out.println("Album ID does not exists\nThe available album IDs are:");
            printTable("Albums");
        }
    }

    public static void deleteRecordLabel() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Record Labels are: ");
        printTable("RecordLabels");

        System.out.print("Enter the Record Label ID of the entry to be deleted: ");
        String recordLabelId = scanner.nextLine();

        if (doesRecordExists("RecordLabels", recordLabelId)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Songs WHERE songID IN (" +
                    "SELECT songID FROM Earners " +
                    "JOIN RecordLabels on Earners.earnerID = RecordLabels.earnerID " +
                    "JOIN Artists on Artists.recordLabelEarnerID = RecordLabels.earnerID " +
                    "JOIN Sings on Artists.artistID = Sings.artistID WHERE Earners.earnerID= \'" + recordLabelId + "\');");


            statement.executeUpdate("DELETE FROM Earners WHERE earnerID = \'" + recordLabelId + "\';");
            System.out.println("Record Label row with ID: " + recordLabelId + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("RecordLabels");

        } else {
            System.out.println("Record Label ID does not exists\nThe available Record Label IDs are:");
            printTable("RecordLabels");
        }
    }

    public static void deleteArtist() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Artists are: ");
        printTable("Artists");

        System.out.print("Enter the Artist ID of the entry to be deleted: ");
        String artistID = scanner.nextLine();

        if (doesRecordExists("Artists", artistID)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Artists WHERE artistID = \'" + artistID + "\';");
            System.out.println("Artist row with ID: " + artistID + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("Artists");

        }
        else{
            System.out.println("Artist ID does not exists\nThe available Artist IDs are:");
            printTable("Artists");
        }
    }

    public static void deleteSong() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the songID of the entry to be deleted: ");
        String songID = scanner.nextLine();

        if (doesRecordExists("Songs", songID)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Songs WHERE songID = \'" + songID + "\';");
            System.out.println("Song row with ID: " + songID + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("Songs");

        } else {
            System.out.println("Song ID does not exists\nThe available Song IDs are:");
            printTable("Songs");
        }
    }

    public static void deletePodcastHost() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Podcast Hosts are: ");
        printTable("PodcastHosts");

        System.out.print("Enter the Podcast Host ID of the entry to be deleted: ");
        String podcastHostId = scanner.nextLine();

        if (doesRecordExists("PodcastHosts", podcastHostId)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM PodcastHosts WHERE podcastHostEarnerId = \'" + podcastHostId + "\';");
            System.out.println("Podcast Host row with ID: " + podcastHostId + " deleted successfully.");

            statement.executeUpdate("DELETE FROM Earners WHERE earnerID = \'" + podcastHostId + "\';");
        // System.out.println("Earners row with ID: " + podcastHostId + " deleted successfully.");

            System.out.print("After Deletions, Podcast Hosts are: ");
            printTable("Podcasts");

        } else {
            System.out.println("Podcast Hosts ID does not exists\nThe available podcast Host IDs are:");
            printTable("Podcasts");
        }
    }

    public static void deletePodcastEpisode() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Podcast Episodes are: ");
        printTable("PodcastEpisodes");

        System.out.print("Enter the Podcast Episode ID of the entry to be deleted: ");
        String podcastEpisodeId = scanner.nextLine();

        if (doesRecordExists("PodcastEpisodes", podcastEpisodeId)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM PodcastEpisodes WHERE podcastEpisodeId = \'" + podcastEpisodeId + "\';");
            System.out.println("Podcast Episode row with ID: " + podcastEpisodeId + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("PodcastEpisodes");

        } else {
            System.out.println("Podcast Episode ID does not exists\nThe available podcast Episode IDs are:");
            printTable("PodcastEpisodes");
        }
    }

    public static void deletePodcast() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Podcasts are: ");
        printTable("Podcasts");

        System.out.print("Enter the Podcast ID of the entry to be deleted: ");
        String podcastId = scanner.nextLine();

        if (doesRecordExists("Podcasts", podcastId)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Podcasts WHERE podcastId = \'" + podcastId + "\';");
            System.out.println("Podcast row with ID: " + podcastId + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("Podcasts");

        } else {
            System.out.println("Podcast ID does not exists\nThe available podcast IDs are:");
            printTable("Podcasts");
        }
    }

    public static void deleteUser() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Existing Users are: ");
        printTable("Users");

        System.out.print("Enter the User ID of the entry to be deleted: ");
        String userId = scanner.nextLine();

        if (doesRecordExists("Users", userId)) {
        //delete the given item

            statement.executeUpdate("DELETE FROM Users WHERE userID = \'" + userId + "\';");
            System.out.println("Podcast row with ID: " + userId + " deleted successfully.");

            System.out.println("After Deletion: ");
            printTable("Users");

        } else {
            System.out.println("User ID does not exists\nThe available user IDs are:");
            printTable("Users");
        }
    }

    /**
     * All users pay the streaming service. The streaming service collects revenue.
     * @throws SQLException
     */
    private static void allUsersPayStreamingService() throws SQLException {
        statement.executeUpdate("INSERT INTO StreamingServiceMonthlyRevenue (monthYear, revenue) VALUES(CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE), (SELECT SUM(monthlySubscriptionFee) FROM Users WHERE subscriptionIsActiveStatus IS TRUE)) ON DUPLICATE KEY UPDATE\n" +
                "    revenue=revenue+(SELECT SUM(monthlySubscriptionFee) FROM Users WHERE subscriptionIsActiveStatus IS TRUE);");
        printTable("StreamingServiceMonthlyRevenue");
    }

    /**
     * Pays all podcast hosts for the current month.
     * Only need to run once per month.
     * @throws SQLException
     */
    public static void servicePaysAllPodcastHosts() throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.executeUpdate("INSERT INTO Pays (earnerID, amount, monthYear)\n" +
                    "SELECT * FROM (SELECT PodcastHosts.podcastHostEarnerId,\n" +
                    "       SUM(PodcastHosts.flatFee + (PodcastHosts.adBonus * IFNULL(podcastEpisodeAdvertisementCount, 0))) AS owed,\n" +
                    "       CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE)                                             AS monthYearEntry\n" +
                    "FROM PodcastEpisodes\n" +
                    "         LEFT JOIN Runs ON Runs.podcastId = PodcastEpisodes.podcastId\n" +
                    "         LEFT JOIN PodcastHosts on Runs.podcastHostEarnerId = PodcastHosts.podcastHostEarnerId\n" +
                    "         LEFT JOIN Earners on Earners.earnerID = PodcastHosts.podcastHostEarnerId\n" +
                    "GROUP BY Runs.podcastHostEarnerId, monthYearEntry) as dt\n" +
                    "ON DUPLICATE KEY UPDATE amount=amount+owed;");
//            int[] myNumbers = {1, 2, 3};
//            System.out.println(myNumbers[10]); // error!
            System.out.println("Insertion into Pays done");
            statement.executeUpdate("INSERT INTO StreamingServiceMonthlyRevenue (SELECT CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE), (SELECT 0-SUM(amount) from Pays WHERE monthYear = date(NOW()) AND earnerID IN (SELECT earnerID FROM PodcastHosts) GROUP BY monthYear)) ON DUPLICATE KEY UPDATE\n" +
                    " revenue = revenue - (SELECT SUM(amount) from Pays WHERE monthYear = CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE) AND earnerID IN (SELECT podcastHostEarnerId FROM PodcastHosts) GROUP BY monthYear);\n");

            connection.commit();
        }
        catch (Exception e) {
            System.err.println("Encountered a problem while executing the transaction. Rolling back.");
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    System.err.println(e);
                    connection.rollback();
                } catch (Exception excep) {
                    System.err.println("Encountered an error in rolling back.");
                    System.err.println(excep);
                }
            }
        }
        finally{
            connection.setAutoCommit(true);
        }
        printTable("Pays");
        printTable("StreamingServiceMonthlyRevenue");
    }

    /**
     * Pays all record labels for the current month. Happens BEFORE record label pays the artists.
     * Only need to run once per month.
     * @throws SQLException
     */
    public static void servicePaysAllRecordLabels() throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Pays all record labels, without deducting from the revenue of the streaming service.
            statement.executeUpdate("INSERT INTO Pays (earnerID, amount, monthYear)\n" +
                    "SELECT * FROM (SELECT RecordLabels.earnerID AS eID,\n" +
                    "                      SUM(IFNULL(Songs.songRoyaltyRatePerPlay, 0)*IFNULL(Songs.playCountCurrentMonth, 0)) AS owed,\n" +
                    "                      CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE) AS currentMonthYear\n" +
                    "               FROM RecordLabels\n" +
                    "                        LEFT JOIN Earners ON Earners.earnerID = RecordLabels.earnerID\n" +
                    "                        LEFT JOIN Artists on RecordLabels.earnerID = Artists.recordLabelEarnerID\n" +
                    "                        LEFT JOIN Sings on Artists.artistID = Sings.artistID\n" +
                    "                        LEFT JOIN Songs ON Sings.songID = Songs.songID\n" +
                    "               GROUP BY eID) as dt\n" +
                    "ON DUPLICATE KEY UPDATE amount=amount+owed;");
            // Deduct payments from the streaming service.
            statement.executeUpdate("INSERT INTO StreamingServiceMonthlyRevenue (SELECT CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE), (SELECT 0-SUM(amount) from Pays WHERE monthYear = CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE) AND earnerID IN (SELECT earnerID FROM RecordLabels) GROUP BY monthYear)) ON DUPLICATE KEY UPDATE\n" +
                    " revenue = revenue - (SELECT SUM(amount) from Pays WHERE monthYear = CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE) AND earnerID IN (SELECT earnerID FROM RecordLabels) GROUP BY monthYear);");
            connection.commit();
        }
        catch (Exception e) {
            System.err.println("Encountered a problem while executing the transaction. Rolling back.");
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    System.err.println(e);
                    connection.rollback();
                } catch (Exception excep) {
                    System.err.println("Encountered an error in rolling back.");
                    System.err.println(excep);
                }
            }
        }
        finally{
            connection.setAutoCommit(true);
        }
        printTable("Pays");
        printTable("StreamingServiceMonthlyRevenue");
    }

    /**
     * Record Labels pay the Artists after they have been paid.
     * @throws SQLException
     */
    private static void recordLabelsPayArtists() throws SQLException {
        /*
        Firstly, create a hashmap that has entries of song and all involved artists
        * */
        HashMap<String, List<String>> songArtistsMap = new HashMap<>();
        ResultSet sings = statement.executeQuery("SELECT * from Sings;");
        while (sings.next())
        {
            String songID = sings.getString("songID");
            String artistID = sings.getString("artistID");

            List<String> artistsList = new ArrayList<>();
            if(songArtistsMap.get(songID) != null) {
                artistsList = songArtistsMap.get(songID);
            }

            artistsList.add(artistID);
            // print the results
            //System.out.println(songID + artistID);
            songArtistsMap.put(songID, artistsList);
            //System.out.println(songArtistsMap);
        }

//        System.out.println("Sings Done. Now collaborates next\n");
        ResultSet collaborates = statement.executeQuery("SELECT * from Collaborates;");
        while (collaborates.next())
        {
            String songID = collaborates.getString("songID");
            String artistIDCollaborated = collaborates.getString("artistIDCollaborated");

            List<String> artistsList = new ArrayList<>();
            if(songArtistsMap.get(songID) != null) {
                artistsList = songArtistsMap.get(songID);
            }

            artistsList.add(artistIDCollaborated);
            // print the results
            //System.out.println(songID + artistIDCollaborated);
            songArtistsMap.put(songID, artistsList);
            //System.out.println(songArtistsMap);
        }

        /*
        Since now we have the map of song to all its artists, we can make royalty payments
        We divide the 70 percent equally among all the artists involved in singing that song
        * */

        //We first get the royalty amount for each song, and then divide it, and insert into PaysArtists table
        //System.out.println(songArtistsMap.keySet());
        for(String songID: songArtistsMap.keySet()) {
            ResultSet royaltyQuery = statement.executeQuery("Select songID, IF(Songs.isSongRoyaltyPaid = 0, IFNULL(Songs.songRoyaltyRatePerPlay, 0)*IFNULL(Songs.playCountCurrentMonth, 0)*0.7, 0) as amount from Songs where songID = \'" + songID + "\';");

            List<String> artistsList = songArtistsMap.get(songID);

            //System.out.println(songID + " : " + artistsList);

            if(royaltyQuery.next()) {
                double royaltyAmount = royaltyQuery.getDouble("amount");

                if(royaltyAmount != 0) {
                    //System.out.println(songID + " : " + royaltyAmount);

                    double equalShare = royaltyAmount / artistsList.size();

                    for (String artist : artistsList) {
                        statement.executeUpdate("INSERT into PaysArtists (artistId, amount, monthYear) VALUES (\'" + artist + "\'," + equalShare + ", CAST(DATE_FORMAT(NOW() ,'%Y-%m-01') as DATE))\n" +
                                "ON DUPLICATE KEY UPDATE amount = amount + " + equalShare + ";");
                    }

                    // Set isSongRoyaltyPaid to 0
                    statement.executeUpdate("UPDATE Songs set isSongRoyaltyPaid = 1 where songID = \'" + songID + "\';");
                }
            }
        }
        printTable("PaysArtists");
    }

    /**
     * Handles all steps of the payments aside from the users paying the streaming service.
     * @throws SQLException
     */
    public static void handleAllMonthlyPaymentsFromService() throws SQLException {
        servicePaysAllPodcastHosts();
        servicePaysAllRecordLabels();
        recordLabelsPayArtists();
    }

    /**
     * Users pay streaming service, then streaming service pays out money to earners.
     * Then, record labels pay artists.
     * @throws SQLException
     */
    public static void fullMonthlyPaymentsLifeCycle() throws SQLException {
        allUsersPayStreamingService();
        handleAllMonthlyPaymentsFromService();
    }

    public static void printAllServiceRevenueReports() throws SQLException {
        printServiceMonthlyRevenue();
        printServiceAnnualRevenue();
        printServiceTotalRevenue();
        System.out.println();
    }

    /**
     * Prints total streaming service revenue overall.
     * @throws SQLException
     */
    public static void printServiceTotalRevenue() throws SQLException {
        System.out.println("Service total revenue:");
        ResultSet totalRevenue = statement.executeQuery("SELECT SUM(revenue) AS total FROM StreamingServiceMonthlyRevenue;");
        while (totalRevenue.next())
        {
            String total = totalRevenue.getString("total");
            // print the results
            System.out.format("%s\n", total);
        }
        System.out.println();
    }

    /**
     * Prints total streaming service revenue by month.
     * @throws SQLException
     */
    public static void printServiceMonthlyRevenue() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT DATE_FORMAT(monthYear, '%b %Y') AS monthYearFormatted, monthYear, revenue FROM StreamingServiceMonthlyRevenue;");
        System.out.println("Service monthly revenue:");
        while (totalRevenue.next())
        {
            String monthYearFormatted = totalRevenue.getString("monthYearFormatted");
            String revenue = totalRevenue.getString("revenue");
            // print the results
            System.out.format("%s: %s\n", monthYearFormatted, revenue);
        }
        System.out.println();
    }

    /**
     * Prints total streaming service revenue by year.
     * @throws SQLException
     */
    public static void printServiceAnnualRevenue() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT YEAR(monthYear) AS year, SUM(revenue) as total FROM StreamingServiceMonthlyRevenue GROUP BY year;");
        System.out.println("Service annual revenue:");
        while (totalRevenue.next())
        {
            String year = totalRevenue.getString("year");
            String revenue = totalRevenue.getString("total");
            // print the results
            System.out.format("%s: %s\n", year, revenue);
        }
        System.out.println();
    }

    /**
     * Prints total payments for each individual record label.
     * Shows this data for all the record labels.
     * @throws SQLException
     */
    public static void printRecordLabelTotalPayments() throws SQLException {
        System.out.println("Record Label total payments:");
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, SUM(amount) AS total FROM Pays WHERE earnerID IN (SELECT earnerID FROM RecordLabels) GROUP BY earnerID;\n");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String total = totalRevenue.getString("total");
            // print the results
            System.out.format("%s: %s\n", id, total);
        }
        System.out.println();
    }

    /**
     * Prints monthly payments for each individual record label.
     * Shows this data for all the record labels.
     * @throws SQLException
     */
    public static void printRecordLabelMonthlyPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, DATE_FORMAT(monthYear, '%b %Y') AS monthYearFormatted, monthYear, SUM(amount) AS revenue FROM Pays WHERE earnerID IN (SELECT RecordLabels.earnerID FROM RecordLabels) GROUP BY earnerID, monthYearFormatted;");
        System.out.println("Record Labels monthly payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String monthYearFormatted = totalRevenue.getString("monthYearFormatted");
            String revenue = totalRevenue.getString("revenue");
            // print the results
            System.out.format("%s, %s: %s\n", id, monthYearFormatted, revenue);
        }
        System.out.println();
    }

    /**
     * Prints annual payments for each individual record label.
     * Shows this data for all the record labels.
     * @throws SQLException
     */
    public static void printRecordLabelAnnualPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, YEAR(monthYear) AS year, SUM(amount) AS total FROM Pays WHERE earnerID IN (SELECT earnerID FROM RecordLabels) GROUP BY earnerID, year;");
        System.out.println("Record Labels annual payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String year = totalRevenue.getString("year");
            String total = totalRevenue.getString("total");
            // print the results
            System.out.format("%s, %s: %s\n", id, year, total);
        }
        System.out.println();
    }

    /**
     * Prints total payments for each individual podcast host.
     * Shows this data for all the podcast hosts.
     * @throws SQLException
     */
    public static void printPodcastHostTotalPayments() throws SQLException {
        System.out.println("Podcast Hosts total payments:");
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, SUM(amount) AS total FROM Pays WHERE earnerID IN (SELECT podcastHostEarnerId FROM PodcastHosts) GROUP BY earnerID;\n");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String total = totalRevenue.getString("total");
            // print the results
            System.out.format("%s: %s\n", id, total);
        }
        System.out.println();
    }

    /**
     * Prints monthly payments for each individual podcast host.
     * Shows this data for all the podcast hosts.
     * @throws SQLException
     */
    public static void printPodcastHostMonthlyPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, DATE_FORMAT(monthYear, '%b %Y') AS monthYearFormatted, monthYear, SUM(amount) AS revenue FROM Pays WHERE earnerID IN (SELECT podcastHostEarnerId FROM PodcastHosts) GROUP BY earnerID, monthYearFormatted;");
        System.out.println("Podcast Hosts monthly payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String monthYearFormatted = totalRevenue.getString("monthYearFormatted");
            String revenue = totalRevenue.getString("revenue");
            // print the results
            System.out.format("%s, %s: %s\n", id, monthYearFormatted, revenue);
        }
        System.out.println();
    }

    /**
     * Prints annual payments for each individual podcast host.
     * Shows this data for all the podcast hosts.
     * @throws SQLException
     */
    public static void printPodcastHostAnnualPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT earnerID, YEAR(monthYear) AS year, SUM(amount) AS total FROM Pays WHERE earnerID IN (SELECT podcastHostEarnerId FROM PodcastHosts) GROUP BY earnerID, year;");
        System.out.println("Podcast Hosts annual payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("earnerID");
            String year = totalRevenue.getString("year");
            String total = totalRevenue.getString("total");
            // print the results
            System.out.format("%s, %s: %s\n", id, year, total);
        }
        System.out.println();
    }

    /**
     * Prints out the total payments for each artist.
     * @throws SQLException
     */
    public static void printArtistTotalPayments() throws SQLException {
        System.out.println("Artist total payments:");
        ResultSet totalRevenue = statement.executeQuery("SELECT PaysArtists.artistId, A.artistName, SUM(amount) AS totalPaymentsTo FROM PaysArtists\n" +
                "    LEFT JOIN Artists A on PaysArtists.artistId = A.artistID\n" +
                "    GROUP BY PaysArtists.artistId;");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("artistId");
            String name = totalRevenue.getString("artistName");
            String total = totalRevenue.getString("totalPaymentsTo");
            // print the results
            System.out.format("%s, %s: %s\n", id, name, total);
        }
        System.out.println();
    }

    /**
     * Prints out the total payments for each artist on a monthly basis.
     * @throws SQLException
     */
    public static void printArtistMonthlyPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT PaysArtists.artistId, A.artistName, DATE_FORMAT(monthYear, '%b %Y') AS monthYearFormatted, monthYear, SUM(amount) AS totalPaymentsTo FROM PaysArtists\n" +
                "    LEFT JOIN Artists A on PaysArtists.artistId = A.artistID\n" +
                "    GROUP BY PaysArtists.artistId, monthYearFormatted;");
        System.out.println("Artist monthly payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("artistId");
            String name = totalRevenue.getString("artistName");
            String monthYearFormatted = totalRevenue.getString("monthYearFormatted");
            String revenue = totalRevenue.getString("totalPaymentsTo");
            // print the results
            System.out.format("%s, %s, %s: %s\n", id, name, monthYearFormatted, revenue);
        }
        System.out.println();
    }

    /**
     * Prints out the total payments for each artist on a yearly basis.
     * @throws SQLException
     */
    public static void printArtistAnnualPayments() throws SQLException {
        ResultSet totalRevenue = statement.executeQuery("SELECT PaysArtists.artistId, A.artistName, YEAR(monthYear) AS year, SUM(amount) AS totalPaymentsTo FROM PaysArtists\n" +
                "    LEFT JOIN Artists A on PaysArtists.artistId = A.artistID\n" +
                "    GROUP BY PaysArtists.artistId, year;");
        System.out.println("Artist annual payments summary:");
        while (totalRevenue.next())
        {
            String id = totalRevenue.getString("artistId");
            String name = totalRevenue.getString("artistName");
            String year = totalRevenue.getString("year");
            String total = totalRevenue.getString("totalPaymentsTo");
            // print the results
            System.out.format("%s, %s, %s: %s\n", id, name, year, total);
        }
        System.out.println();
    }

    public static void showSongInfo(String songId) throws SQLException{
        String sQuery = "SELECT * FROM Songs WHERE songID = \'" + songId + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static void showAlbumInfo(String albumId) throws SQLException{
        String sQuery = "SELECT * FROM Albums WHERE albumID = \'" + albumId + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static void showArtistInfo(String artistId) throws SQLException{
        String sQuery = "SELECT * FROM Artists WHERE artistID = \'" + artistId + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static void showPodcastHostInfo(String podcastHostId) throws SQLException{
        String sQuery = "SELECT * FROM PodcastHosts WHERE podcastHostEarnerId = \'" + podcastHostId + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static void showPodcastInfo(String podcastID) throws SQLException{
        String sQuery = "SELECT * FROM Podcasts WHERE podcastId = \'" + podcastID + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static void showPodcastEpisodeInfo(String podcastEpisodetID) throws SQLException{
        String sQuery = "SELECT * FROM PodcastEpisodes WHERE podcastEpisodeId = \'" + podcastEpisodetID + "\'";
        ResultSet rs = statement.executeQuery(sQuery);
        DBTablePrinter.printResultSet(rs);
    }

    public static boolean validateEmail(String email) {
        String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        // regular expression for phone number
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Shows all podcasts for selection purposes.
     * @throws SQLException
     */
    public static void showAllPodcasts() throws SQLException {
        String query = "SELECT * FROM Podcasts;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("All Podcasts: ");
        while (rs.next())
        {
            String id = rs.getString("podcastId");
            String podcastName = rs.getString("podcastName");
            // print the results
            System.out.format("%s, %s\n", id, podcastName);
        }
        System.out.println();
    }

    public static void showAllPodcastHosts() throws SQLException {
        String query = "SELECT * FROM PodcastHosts;";
        ResultSet rs = statement.executeQuery(query);
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * Prints out all podcast episodes corresponding to a given podcast id.
     * @param podcastId
     * @throws SQLException
     */
    public static void printPodcastEpisodesForPodcast(String podcastId) throws SQLException {
        String query = "SELECT * FROM PodcastEpisodes WHERE podcastId = '" + podcastId + "';";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Podcast episodes for selected podcast: ");
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * Prints out all podcast episodes corresponding to a given podcast host id.
     * @param podcastHostId
     * @throws SQLException
     */
    public static void printPodcastEpisodesForPodcastHost(String podcastHostId) throws SQLException {
        String query = "SELECT PodcastEpisodes.* FROM PodcastEpisodes\n" +
                "    LEFT JOIN Runs R on PodcastEpisodes.podcastId = R.podcastId\n" +
                "    LEFT JOIN PodcastHosts PH on PH.podcastHostEarnerId = R.podcastHostEarnerId\n" +
                "    WHERE PH.podcastHostEarnerId = '" + podcastHostId + "';";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Podcast episodes for selected podcast host: ");
        DBTablePrinter.printResultSet(rs);
    }

    /**
     * Prints out all the payments for podcast hosts between a starting time and
     * end time, inclusive.
     * @param startTime
     * @param endTime
     * @throws SQLException
     */
    public static void printPaymentsInTimePeriodPodcastHosts(String startTime, String endTime) throws SQLException {
        String query = "SELECT earnerID, SUM(amount) AS total FROM Pays\n" +
                "    WHERE monthYear >= '" + startTime + "' AND monthYear <= '" + endTime + "'\n" +
                "    AND earnerID IN (SELECT DISTINCT podcastHostEarnerId FROM PodcastHosts)\n" +
                "    GROUP BY earnerID\n" +
                "    ORDER BY earnerID;";
        //("2023-02-01", "2023-03-01")
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Payments For PodcastHosts Over Given Time Period: ");
        while (rs.next())
        {
            String id = rs.getString("earnerID");
            String total = rs.getString("total");
            // print the results
            System.out.format("%s, %s\n", id, total);
        }
        System.out.println();
    }

    /**
     * Prints out all the payments for record labels between a starting time and
     * end time, inclusive.
     * @param startTime
     * @param endTime
     * @throws SQLException
     */
    public static void printPaymentsInTimePeriodRecordLabels(String startTime, String endTime) throws SQLException {
        String query = "SELECT earnerID, SUM(amount) AS total FROM Pays\n" +
                "    WHERE monthYear >= '" + startTime + "' AND monthYear <= '" + endTime + "'\n" +
                "    AND earnerID IN (SELECT DISTINCT earnerID FROM RecordLabels)\n" +
                "    GROUP BY earnerID\n" +
                "    ORDER BY earnerID;";
        //("2023-02-01", "2023-03-01")
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Payments For RecordLabels Over Given Time Period: ");
        while (rs.next())
        {
            String id = rs.getString("earnerID");
            String total = rs.getString("total");
            // print the results
            System.out.format("%s, %s\n", id, total);
        }
        System.out.println();
    }

    /**
     * Prints out all the payments for artists between a starting time and
     * end time, inclusive.
     * @param startTime
     * @param endTime
     * @throws SQLException
     */
    public static void printPaymentsInTimePeriodArtists(String startTime, String endTime) throws SQLException {
        String query = "SELECT artistID, SUM(amount) as total FROM PaysArtists\n" +
                "    WHERE monthYear >= '" + startTime + "' AND monthYear <= '" + endTime + "'\n" +
                "    GROUP BY artistID\n" +
                "    ORDER BY artistID;";
        //("2023-02-01", "2023-03-01")
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println(rs);
        System.out.println("Payments For Artists Over Given Time Period: ");
        while (rs.next())
        {
//            System.out.println("Printing Row number");
            String id = rs.getString("artistID");
            String total = rs.getString("total");
            // print the results
            System.out.format("%s, %s\n", id, total);
        }
        System.out.println();
    }

    /**
     * Prints out the monthly play counts for each individual song.
     * @throws SQLException
     */
    public static void printMonthlyPlayCountsForSongs() throws SQLException {
        String query = "SELECT SongsLogs.songId, S.songTitle, DATE_FORMAT(songLogMonthYear, '%b %Y') AS monthYear, playCount FROM SongsLogs\n" +
                "    LEFT JOIN Songs S on S.songID = SongsLogs.songId\n" +
                "    ORDER BY monthYear, SongsLogs.songId;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Monthly play counts per Song.: ");
        System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", "Song ID: ", "Song Title: ", "Month Year: ", "Play Count: ");
        while (rs.next())
        {
            String id = rs.getString("songId");
            String songTitle = rs.getString("songTitle");
            String monthYear = rs.getString("monthYear");
            String playCount = rs.getString("playCount");
            // print the results
            System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", id, songTitle, monthYear, playCount);
        }
        System.out.println();
    }

    /**
     * Prints out the annual play counts for each individual song.
     * @throws SQLException
     */
    public static void printYearlyPlayCountsForSongs() throws SQLException {
        String query = "SELECT SongsLogs.songId, S.songTitle, YEAR(songLogMonthYear) AS year, SUM(playCount) AS playCountTotal FROM SongsLogs\n" +
                "    LEFT JOIN Songs S on S.songID = SongsLogs.songId\n" +
                "    GROUP BY SongsLogs.songId\n" +
                "    ORDER BY year, SongsLogs.songId;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Yearly play counts per Song.: ");
        System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", "Song ID: ", "Song Title: ", "Year: ", "Play Count: ");
        while (rs.next())
        {
            String id = rs.getString("songId");
            String songTitle = rs.getString("songTitle");
            String year = rs.getString("year");
            String playCountTotal = rs.getString("playCountTotal");
            // print the results
            System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", id, songTitle, year, playCountTotal);
        }
        System.out.println();
    }

    /**
     * Prints out the total play counts for each individual song.
     * @throws SQLException
     */
    public static void printTotalPlayCountsForSongs() throws SQLException {
        String query = "SELECT SongsLogs.songId, S.songTitle, SUM(playCount) AS playCountTotal FROM SongsLogs\n" +
                "    LEFT JOIN Songs S on S.songID = SongsLogs.songId\n" +
                "    ORDER BY SongsLogs.songId;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Total play counts per Song.: ");
        System.out.format("%10s    |     %20s    |     %10s\n", "Song ID: ", "Song Title: ", "Play Count: ");
        while (rs.next())
        {
            String id = rs.getString("songId");
            String songTitle = rs.getString("songTitle");
            String playCount = rs.getString("playCountTotal");
            // print the results
            System.out.format("%10s    |     %20s    |     %10s\n", id, songTitle, playCount);
        }
        System.out.println();
    }

    /**
     * Prints out the monthly play counts for each artist, given all of their songs.
     * @throws SQLException
     */
    public static void printMonthlyPlayCountsForArtists() throws SQLException {
        String query = "SELECT A.artistID, A.artistName, DATE_FORMAT(songLogMonthYear, '%b %Y') AS monthYear, SUM(playCount) AS totalCount\n" +
                "FROM SongsLogs\n" +
                "    LEFT JOIN Songs S on S.songID = SongsLogs.songId\n" +
                "    LEFT JOIN Sings Si on S.songID = Si.songID\n" +
                "    LEFT JOIN Artists A on Si.artistID = A.artistID\n" +
                "GROUP BY A.artistID, monthYear\n" +
                "ORDER BY artistID, monthYear;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Monthly play counts per Artist.: ");
        System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", "Song ID: ", "Artist Name: ", "Month Year: ", "Play Count: ");
        while (rs.next())
        {
            String id = rs.getString("artistId");
            String artistName = rs.getString("artistName");
            String monthYear = rs.getString("monthYear");
            String totalCount = rs.getString("totalCount");
            // print the results
            System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", id, artistName, monthYear, totalCount);
        }
        System.out.println();
    }

    /**
     * Prints out the monthly play counts for each album, given all of their songs.
     * @throws SQLException
     */
    public static void printMonthlyPlayCountsForAlbums() throws SQLException {
        String query = "SELECT A.albumID, A.albumName, DATE_FORMAT(songLogMonthYear, '%b %Y') AS monthYear, SUM(playCount) AS totalCount\n" +
                "FROM SongsLogs\n" +
                "         LEFT JOIN Songs S on S.songID = SongsLogs.songId\n" +
                "         LEFT JOIN Albums A on S.albumID = A.albumID\n" +
                "GROUP BY A.albumID, monthYear\n" +
                "ORDER BY albumID, monthYear;";
        ResultSet rs = statement.executeQuery(query);
        System.out.println();
        System.out.println("Monthly play counts per Album.: ");
        System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", "Album ID: ", "Album Name: ", "Month Year: ", "Play Count: ");
        while (rs.next())
        {
            String id = rs.getString("albumID");
            String albumName = rs.getString("albumName");
            String monthYear = rs.getString("monthYear");
            String totalCount = rs.getString("totalCount");
            // print the results
            System.out.format("%10s    |     %20s    |     %15s    |     %10s\n", id, albumName, monthYear, totalCount);
        }
        System.out.println();
    }

    /**
     * Prints all of the payments and streaming service revenue reports.
     * Does so for total, monthly, and annual groupings of time.
     * @throws SQLException
     */
    public static void printAllFinancialReports() throws SQLException {
        printServiceTotalRevenue();
        printServiceMonthlyRevenue();
        printServiceAnnualRevenue();
        printRecordLabelTotalPayments();
        printRecordLabelMonthlyPayments();
        printRecordLabelAnnualPayments();
        printPodcastHostTotalPayments();
        printPodcastHostMonthlyPayments();
        printPodcastHostAnnualPayments();
        printArtistTotalPayments();
        printArtistMonthlyPayments();
        printArtistAnnualPayments();
    }

    /**
     * Creates all of the tables for our database.
     */
    public static void createTables() {
        try {
            statement.executeUpdate("CREATE TABLE Users\n" +
                    "(\n" +
                    "    userID                     VARCHAR(255)  NOT NULL,\n" +
                    "    userFirstName              VARCHAR(255)  NOT NULL,\n" +
                    "    userLastName               VARCHAR(255)  NOT NULL,\n" +
                    "    subscriptionIsActiveStatus VARCHAR(255)  NOT NULL,\n" +
                    "    monthlySubscriptionFee     DOUBLE        NOT NULL,\n" +
                    "    userEmail                  NVARCHAR(255) NOT NULL,\n" +
                    "    registrationDate           DATE          NOT NULL,\n" +
                    "    PRIMARY KEY (userID)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE StreamingServiceMonthlyRevenue(\n" +
                    "    monthYear       DATE           NOT NULL,\n" +
                    "    revenue         DOUBLE(9, 2)   NOT NULL,\n" +
                    "    PRIMARY KEY (monthYear)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Earners(\n" +
                    "    earnerID VARCHAR(255) NOT NULL PRIMARY KEY\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE RecordLabels(\n" +
                    "    earnerID        VARCHAR(255)    NOT NULL,\n" +
                    "    recordLabelName VARCHAR(255)    NOT NULL,\n" +
                    "    PRIMARY KEY (earnerID),\n" +
                    "    FOREIGN KEY (earnerID) REFERENCES Earners (earnerID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastHosts(\n" +
                    "    podcastHostEarnerId  varchar(255) NOT NULL,\n" +
                    "    podcastHostFirstName varchar(255) NOT NULL,\n" +
                    "    podcastHostLastName  varchar(255) NOT NULL,\n" +
                    "    podcastHostEmail     varchar(255) NOT NULL,\n" +
                    "    podcastHostPhone     char(10),\n" +
                    "    podcastHostCity      varchar(50),\n" +
                    "    flatFee              DOUBLE(9, 2) NOT NULL,\n" +
                    "    adBonus              DOUBLE(9, 2) NOT NULL,\n" +
                    "    PRIMARY KEY (podcastHostEarnerId),\n" +
                    "    FOREIGN KEY (podcastHostEarnerId) REFERENCES Earners (earnerID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE SongGenres(\n" +
                    "    songGenreName VARCHAR(255) NOT NULL PRIMARY KEY\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Artists(\n" +
                    "    artistID               VARCHAR(255) NOT NULL,\n" +
                    "    recordLabelEarnerID    VARCHAR(255) NOT NULL,\n" +
                    "    artistName             VARCHAR(255) NOT NULL,\n" +
                    "    artistStatusIsActive   VARCHAR(255) NOT NULL,\n" +
                    "    artistMonthlyListeners INT          NOT NULL,\n" +
                    "    artistPrimaryGenre     VARCHAR(255),\n" +
                    "    artistType             VARCHAR(255),\n" +
                    "    artistCountry          VARCHAR(255),\n" +
                    "    PRIMARY KEY (artistID),\n" +
                    "    FOREIGN KEY (recordLabelEarnerID) REFERENCES RecordLabels (earnerID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (artistPrimaryGenre) REFERENCES SongGenres (songGenreName)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Albums(\n" +
                    "    albumID           VARCHAR(255) NOT NULL,\n" +
                    "    albumName         VARCHAR(255) NOT NULL,\n" +
                    "    albumEdition      VARCHAR(255),\n" +
                    "    albumTrackNumbers INT          NOT NULL,\n" +
                    "    albumReleaseYear  YEAR         NOT NULL,\n" +
                    "    PRIMARY KEY (albumID)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Songs(\n" +
                    "    songID                 VARCHAR(255)      NOT NULL,\n" +
                    "    songTitle              VARCHAR(255)      NOT NULL,\n" +
                    "    albumID                VARCHAR(255)      NOT NULL,\n" +
                    "    playCountCurrentMonth  INT               NOT NULL DEFAULT 0,\n" +
                    "    songRoyaltyRatePerPlay DOUBLE(9,2)       NOT NULL,\n" +
                    "    isSongRoyaltyPaid      VARCHAR(255)      NOT NULL DEFAULT 'no',\n" +
                    "    songReleaseDate        DATE              NOT NULL,\n" +
                    "    songLanguage           VARCHAR(255),\n" +
                    "    songDuration           TIME,\n" +
                    "    PRIMARY KEY (songID),\n" +
                    "    FOREIGN KEY (albumID) REFERENCES Albums (albumID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Sings(\n" +
                    "    artistID        VARCHAR(255)    NOT NULL,\n" +
                    "    songID          VARCHAR(255)    NOT NULL,\n" +
                    "    PRIMARY KEY (artistId, songID),\n" +
                    "    FOREIGN KEY (songID)\n" +
                    "      REFERENCES Songs (songID)\n" +
                    "      ON UPDATE CASCADE\n" +
                    "      ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (artistId) REFERENCES Artists (artistId)\n" +
                    "      ON UPDATE CASCADE\n" +
                    "      ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Collaborates(\n" +
                    "     artistIDMain            VARCHAR(255) NOT NULL,\n" +
                    "     artistIDCollaborated    VARCHAR(255) NOT NULL,\n" +
                    "     songID                  VARCHAR(255) NOT NULL,\n" +
                    "     PRIMARY KEY (artistIDMain, artistIDCollaborated, songID),\n" +
                    "     FOREIGN KEY (artistIDMain)\n" +
                    "         REFERENCES Artists (artistID)\n" +
                    "         ON UPDATE CASCADE\n" +
                    "         ON DELETE CASCADE,\n" +
                    "     FOREIGN KEY (artistIDCollaborated)\n" +
                    "         REFERENCES Artists (artistID)\n" +
                    "         ON UPDATE CASCADE\n" +
                    "         ON DELETE CASCADE,\n" +
                    "     FOREIGN KEY (songID)\n" +
                    "         REFERENCES Songs (songID)\n" +
                    "         ON UPDATE CASCADE\n" +
                    "         ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE SongsLogs(\n" +
                    "    songId           VARCHAR(255) NOT NULL,\n" +
                    "    playCount        INT          NOT NULL,\n" +
                    "    songLogMonthYear DATE NOT NULL,\n" +
                    "    PRIMARY KEY (songId, songLogMonthYear),\n" +
                    "    FOREIGN KEY (songId) REFERENCES Songs (songId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    "\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE SongBelongsTo(\n" +
                    "    songID          VARCHAR(255) NOT NULL,\n" +
                    "    songGenreName   VARCHAR(255) NOT NULL,\n" +
                    "    PRIMARY KEY (songID, songGenreName),\n" +
                    "    FOREIGN KEY  (songID)\n" +
                    "        REFERENCES Songs (songID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (songGenreName)\n" +
                    "        REFERENCES SongGenres (songGenreName)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Pays(\n" +
                    "    earnerID  VARCHAR(255) NOT NULL,\n" +
                    "    amount    DOUBLE(9, 2) NOT NULL,\n" +
                    "    monthYear DATE NOT NULL,\n" +
                    "    PRIMARY KEY (earnerID, monthYear),\n" +
                    "    FOREIGN KEY (earnerID)\n" +
                    "        REFERENCES Earners (earnerID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PaysArtists(\n" +
                    "    artistId  VARCHAR(255) NOT NULL,\n" +
                    "    amount    DOUBLE(9, 2) NOT NULL,\n" +
                    "    monthYear DATE NOT NULL,\n" +
                    "    PRIMARY KEY (artistId, monthYear),\n" +
                    "    FOREIGN KEY (artistId)\n" +
                    "        REFERENCES Artists (artistID)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Podcasts\n" +
                    "(\n" +
                    "    podcastId               VARCHAR(255) NOT NULL,\n" +
                    "    podcastName             VARCHAR(255) NOT NULL,\n" +
                    "    podcastEpisodeCount     INT,\n" +
                    "    flatFeePerEpisode       DOUBLE(9, 2) NOT NULL,\n" +
                    "    podcastRating           DECIMAL(2, 1),\n" +
                    "    podcastTotalSubscribers INT,\n" +
                    "    podcastLanguage         VARCHAR(255),\n" +
                    "    podcastCountry          VARCHAR(255),\n" +
                    "    PRIMARY KEY (podcastId)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastEpisodes\n" +
                    "(\n" +
                    "    podcastEpisodeId                 VARCHAR(255) NOT NULL,\n" +
                    "    podcastEpisodeTitle              VARCHAR(255) NOT NULL,\n" +
                    "    podcastId                        VARCHAR(255) NOT NULL,\n" +
                    "    podcastEpisodeListeningCount     INT DEFAULT 0,\n" +
                    "    podcastEpisodeAdvertisementCount INT DEFAULT 0,\n" +
                    "    podcastEpisodeDuration           TIME,\n" +
                    "    podcastEpisodeReleaseDate        DATE,\n" +
                    "    FOREIGN KEY (podcastId) REFERENCES Podcasts (podcastId)\n" +
                    "  ON UPDATE CASCADE\n" +
                            "        ON DELETE CASCADE,\n" +
                    "    PRIMARY KEY (podcastEpisodeId)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastSponsors(\n" +
                    "    podcastSponsorName VARCHAR(255) PRIMARY KEY\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastSponsoredBy(\n" +
                    "    podcastSponsorName varchar(255),\n" +
                    "    podcastId          varchar(255),\n" +
                    "    PRIMARY KEY (podcastSponsorName, podcastId),\n" +
                    "    FOREIGN KEY (podcastSponsorName)\n" +
                    "        REFERENCES PodcastSponsors (podcastSponsorName)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (podcastId)\n" +
                    "        REFERENCES Podcasts (podcastId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastGenres(\n" +
                    "    podcastGenreName VARCHAR(255) PRIMARY KEY\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE SpecialGuests\n" +
                    "(\n" +
                    "    guestName varchar(255) NOT NULL,\n" +
                    "    PRIMARY KEY (guestName)\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Runs(\n" +
                    "    podcastHostEarnerId varchar(255) NOT NULL,\n" +
                    "    podcastId           varchar(255) NOT NULL,\n" +
                    "    PRIMARY KEY (podcastHostEarnerId, podcastId),\n" +
                    "    FOREIGN KEY (podcastHostEarnerId)\n" +
                    "        REFERENCES PodcastHosts (podcastHostEarnerId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (podcastId)\n" +
                    "        REFERENCES Podcasts (podcastId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastBelongsTo(\n" +
                    "    podcastGenreName varchar(255),\n" +
                    "    podcastId      varchar(255),\n" +
                    "    PRIMARY KEY (podcastGenreName, podcastId),\n" +
                    "    FOREIGN KEY (podcastGenreName)\n" +
                    "        REFERENCES PodcastGenres (podcastGenreName)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (podcastId)\n" +
                    "        REFERENCES Podcasts (podcastId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE Features(\n" +
                    "    guestName             varchar(255)          NOT NULL,\n" +
                    "    podcastEpisodeId         varchar(255) NOT NULL,\n" +
                    "    PRIMARY KEY (guestName, podcastEpisodeId),\n" +
                    "    FOREIGN KEY (guestName)\n" +
                    "        REFERENCES SpecialGuests (guestName)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (podcastEpisodeId)\n" +
                    "        REFERENCES PodcastEpisodes (podcastEpisodeId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");
            statement.executeUpdate("CREATE TABLE PodcastLogs (\n" +
                    "    podcastId VARCHAR(255) NOT NULL,\n" +
                    "    totalSubscribers int(11),\n" +
                    "    rating decimal(2,1),\n" +
                    "    podcastLogMonthYear DATE NOT NULL,\n" +
                    "    PRIMARY KEY (podcastId, podcastLogMonthYear),\n" +
                    "    FOREIGN KEY (podcastId) REFERENCES Podcasts (podcastId)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts all of the initial data into the SQL tables, including demo data.
     */
    public static void insertRows() {
        try {
            statement.executeUpdate("INSERT INTO Users (userID, userFirstName, userLastName, subscriptionIsActiveStatus, monthlySubscriptionFee, userEmail, registrationDate)\n" +
                    "VALUES\n" +
                    "    ('u8001', 'Alex', 'A', true, 10, 'alex.a@ncsu.edu', NOW()),\n" +
                    "    ('u8002', 'John', 'J', true, 10, 'john.j@ncsu.edu', NOW());");
            statement.executeUpdate("INSERT INTO StreamingServiceMonthlyRevenue(monthYear, revenue)\n" +
                    "VALUES\n" +
                    "    ('2023-01-01', 1111),\n" +
                    "    ('2023-02-01', 2222),\n" +
                    "    ('2023-03-01', 3333),\n" +
                    "    ('2023-04-01', 123000);");
            statement.executeUpdate("INSERT INTO Earners (earnerID)\n" +
                    "    VALUES ('rl3001'), ('rl3002'), ('ph6001');");
            statement.executeUpdate("INSERT INTO RecordLabels (earnerID, recordLabelName)\n" +
                    "    VALUES\n" +
                    "        ('rl3001', 'Elevate Records'),\n" +
                    "        ('rl3002', 'Melodic Avenue Music');");
            statement.executeUpdate("INSERT INTO PodcastHosts (podcastHostEarnerId, podcastHostFirstName, podcastHostLastName, podcastHostEmail, podcastHostPhone, podcastHostCity, flatFee, adBonus)\n" +
                    "VALUES\n" +
                    "    ('ph6001', 'Matthew', 'Wilson', 'mwilson@gmail.com', '9195154000', 'San Diego', 5000, 200);");
            statement.executeUpdate("INSERT INTO SongGenres (songGenreName)\n" +
                    "    VALUES ('Pop'), ('Rock'), ('Classical'), ('Jazz'), ('Country');");
            statement.executeUpdate("INSERT INTO Artists (artistID, recordLabelEarnerID, artistName, artistStatusIsActive,\n" +
                    "                     artistMonthlyListeners, artistPrimaryGenre, artistType, artistCountry)\n" +
                    "    VALUES\n" +
                    "        ('ar2001', 'rl3001', 'Forest', 'active', 25, 'Pop', 'band', 'US'),\n" +
                    "        ('ar2002', 'rl3002', 'Rain', 'active', 55, 'Rock', 'musician', 'US');");
            statement.executeUpdate("INSERT INTO Albums(albumID, albumName, albumEdition, albumTrackNumbers, albumReleaseYear)\n" +
                    "    VALUES\n" +
                    "        ('al4001', 'Electric Oasis', '1st', 2, 2008),\n" +
                    "        ('al4002', 'Lost in the Echoes', '2nd', 2, 2009);");
            statement.executeUpdate("INSERT INTO Songs(songID, songTitle, albumID, playCountCurrentMonth, songRoyaltyRatePerPlay, isSongRoyaltyPaid, songReleaseDate, songLanguage,\n" +
                    "                  songDuration)\n" +
                    "    VALUES\n" +
                    "        ('s1001', 'Electric Dreamscape', 'al4001', 500, 0.10, false, '2000-12-12', 'English', '0:3:30'),\n" +
                    "        ('s1002', 'Midnight Mirage', 'al4001', 1000, 0.10, false, '2001-12-12', 'English', '0:3:30'),\n" +
                    "        ('s1003', 'Echoes of You', 'al4002', 100, 0.10, false, '2002-12-12', 'English', '0:3:30'),\n" +
                    "        ('s1004', 'Rainy Nights', 'al4002', 200, 0.10, false, '2003-12-12', 'English', '0:3:30');\n");
            statement.executeUpdate("INSERT INTO Sings(artistID, songID)\n" +
                    "VALUES\n" +
                    "    ('ar2001', 's1001'),\n" +
                    "    ('ar2001', 's1002'),\n" +
                    "    ('ar2002', 's1003'),\n" +
                    "    ('ar2002', 's1004');");
            statement.executeUpdate("INSERT INTO Collaborates\n" +
                    "VALUES\n" +
                    "    ('ar2001', 'ar2002', 's1002');");
            statement.executeUpdate("INSERT INTO SongsLogs(songID, playCount, songLogMonthYear)\n" +
                    "    VALUES\n" +
                    "        ('s1001', 10, '2023-01-01'),\n" +
                    "        ('s1001', 20, '2023-02-01'),\n" +
                    "        ('s1001', 30, '2023-03-01'),\n" +
                    "        ('s1002', 100, '2023-01-01'),\n" +
                    "        ('s1002', 200, '2023-02-01'),\n" +
                    "        ('s1002', 300, '2023-03-01'),\n" +
                    "        ('s1003', 1000, '2023-01-01'),\n" +
                    "        ('s1003', 2000, '2023-02-01'),\n" +
                    "        ('s1003', 3000, '2023-03-01'),\n" +
                    "        ('s1004', 10000, '2023-01-01'),\n" +
                    "        ('s1004', 20000, '2023-02-01'),\n" +
                    "        ('s1004', 30000, '2023-03-01');");
            statement.executeUpdate("INSERT INTO SongBelongsTo(songID, songGenreName)\n" +
                    "    VALUES\n" +
                    "        ('s1001', 'Classical'),\n" +
                    "        ('s1002', 'Rock'),\n" +
                    "        ('s1003', 'Pop'),\n" +
                    "        ('s1004', 'Classical');");
            statement.executeUpdate("INSERT INTO Pays(earnerID, amount, monthYear)\n" +
                    "    VALUES\n" +
                    "        ('ph6001', 20, '2023-01-01'),\n" +
                    "        ('ph6001', 30, '2023-02-01'),\n" +
                    "        ('ph6001', 40, '2023-03-01'),\n" +
                    "        ('rl3001', 3.3, '2023-01-01'),\n" +
                    "        ('rl3001', 6.6, '2023-02-01'),\n" +
                    "        ('rl3001', 9.9, '2023-03-01'),\n" +
                    "        ('rl3002', 330, '2023-01-01'),\n" +
                    "        ('rl3002', 660, '2023-02-01'),\n" +
                    "        ('rl3002', 990, '2023-03-01');");
            statement.executeUpdate("INSERT INTO PaysArtists(artistId, amount, monthYear)\n" +
                    "VALUES\n" +
                    "    ('ar2001', 4.2, '2023-01-01'),\n" +
                    "    ('ar2001', 8.4, '2023-02-01'),\n" +
                    "    ('ar2001', 12.6, '2023-03-01'),\n" +
                    "    ('ar2002', 703.5, '2023-01-01'),\n" +
                    "    ('ar2002', 1547, '2023-02-01'),\n" +
                    "    ('ar2002', 2320.5, '2023-03-01');");
            statement.executeUpdate("INSERT INTO Podcasts(podcastId, podcastName, podcastEpisodeCount, flatFeePerEpisode, podcastRating, podcastTotalSubscribers, podcastLanguage, podcastCountry)\n" +
                    "    VALUES\n" +
                    "        ('p5001','Mind Over Matter: Exploring the Power of the Human Mind', 5, 10, 4.5, 10, 'English', 'United States');");
            statement.executeUpdate("INSERT INTO PodcastEpisodes(podcastEpisodeId, podcastEpisodeTitle, podcastId, podcastEpisodeListeningCount, podcastEpisodeAdvertisementCount, podcastEpisodeDuration, podcastEpisodeReleaseDate)\n" +
                    "    VALUES\n" +
                    "        ('pe7001', 'The Science of Mindfulness', 'p5001', 100, null, '1:22:15', '2018-01-01'),\n" +
                    "        ('pe7002', 'Unlocking Your Potential', 'p5001', 200, null, '1:20:30', '2018-02-01');");
            statement.executeUpdate("INSERT INTO PodcastSponsors(podcastSponsorName)\n" +
                    "    VALUES\n" +
                    "        ('ExpressVPN'),\n" +
                    "        ('ZipRecruiter'),\n" +
                    "        ('Audible'),\n" +
                    "        ('DoorDash'),\n" +
                    "        ('Apple'),\n" +
                    "        ('IBM'),\n" +
                    "        ('CapitalOne'),\n" +
                    "        ('BetterHelp'),\n" +
                    "        ('Comcast');");
            statement.executeUpdate("INSERT INTO PodcastSponsoredBy(podcastSponsorName, podcastId)\n" +
                    "    VALUES\n" +
                    "        ('DoorDash', 'p5001'),\n" +
                    "        ('Audible', 'p5001'),\n" +
                    "        ('ExpressVPN', 'p5001');");
            statement.executeUpdate("INSERT INTO PodcastGenres(podcastGenreName)\n" +
                    "    VALUES\n" +
                    "        ('Crime'),\n" +
                    "        ('Comedy'),\n" +
                    "        ('Business'),\n" +
                    "        ('Kids'),\n" +
                    "        ('Sports'),\n" +
                    "        ('News'),\n" +
                    "        ('Interview'),\n" +
                    "        ('History'),\n" +
                    "        ('Politics');");
            statement.executeUpdate("INSERT INTO SpecialGuests(guestName)\n" +
                    "    VALUES\n" +
                    "        ('James Bond'),\n" +
                    "        ('Tony Stark'),\n" +
                    "        ('Elon Musk'),\n" +
                    "        ('Tim Cook'),\n" +
                    "        ('Penelope Cruz'),\n" +
                    "        ('Shakira');");
            statement.executeUpdate("INSERT INTO Runs(podcastHostEarnerId, podcastId)\n" +
                    "    VALUES\n" +
                    "        ('ph6001', 'p5001');");
            statement.executeUpdate("INSERT INTO PodcastBelongsTo(podcastGenreName, podcastId)\n" +
                    "    VALUES\n" +
                    "        ('Interview', 'p5001');");
            statement.executeUpdate("INSERT INTO Features(guestName, podcastEpisodeId)\n" +
                    "    VALUES\n" +
                    "        ('James Bond', 'pe7001'),\n" +
                    "        ('Penelope Cruz', 'pe7001'),\n" +
                    "        ('Tony Stark', 'pe7002');");
            statement.executeUpdate("INSERT INTO PodcastLogs(podcastId, totalSubscribers, rating, podcastLogMonthYear)\n" +
                    "VALUES\n" +
                    "    ('p5001', 9, 3.2, '2023-01-01'),\n" +
                    "    ('p5001', 20, 3.3, '2023-02-01'),\n" +
                    "    ('p5001', 15, 4.0, '2023-03-01'),\n" +
                    "    ('p5001', 10, 4.5, '2023-04-01');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the database, but connection handling is done outside of this function.
     */
    public static void initialize() {
        try {
            dropAllTables();
            createTables();
            insertRows();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database.
     */
    private static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Drops all tables. Used for helping reset the database.
     */
    private static void dropAllTables() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastLogs");
            statement.executeUpdate("DROP TABLE IF EXISTS Features");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastBelongsTo");
            statement.executeUpdate("DROP TABLE IF EXISTS Runs");
            statement.executeUpdate("DROP TABLE IF EXISTS SpecialGuests");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastGenres");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastSponsoredBy");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastSponsors");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastEpisodes");
            statement.executeUpdate("DROP TABLE IF EXISTS Podcasts");
            statement.executeUpdate("DROP TABLE IF EXISTS PaysArtists");
            statement.executeUpdate("DROP TABLE IF EXISTS Pays");
            statement.executeUpdate("DROP TABLE IF EXISTS SongBelongsTo");
            statement.executeUpdate("DROP TABLE IF EXISTS SongsLogs");
            statement.executeUpdate("DROP TABLE IF EXISTS Collaborates");
            statement.executeUpdate("DROP TABLE IF EXISTS Sings");
            statement.executeUpdate("DROP TABLE IF EXISTS Songs");
            statement.executeUpdate("DROP TABLE IF EXISTS Albums");
            statement.executeUpdate("DROP TABLE IF EXISTS Artists");
            statement.executeUpdate("DROP TABLE IF EXISTS SongGenres");
            statement.executeUpdate("DROP TABLE IF EXISTS PodcastHosts");
            statement.executeUpdate("DROP TABLE IF EXISTS RecordLabels");
            statement.executeUpdate("DROP TABLE IF EXISTS Earners");
            statement.executeUpdate("DROP TABLE IF EXISTS StreamingServiceMonthlyRevenue");
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            //} catch (SQLException e) {
        } catch (Exception ignored) {
        }
    }

    /**
     * Connects to the database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void connectToDatabase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, USERNAME, PW);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new SQLException("Could not connect to database.");
        }
        catch (Exception e) {
            throw new ClassNotFoundException("Class for driver not found.");
        }
    }
}
