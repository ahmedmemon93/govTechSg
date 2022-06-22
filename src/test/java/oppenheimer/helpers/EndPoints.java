package oppenheimer.helpers;

public class EndPoints {
    public static final String INSERT_RECORD_END_POINT = "/calculator/insert";
    public static final String INSERT_RECORD_MULTIPLE_END_POINT = "/calculator/insertMultiple";
    public static final String TAX_RELIEF_END_POINT = "/calculator/taxRelief";
    public static String DATABASE_PURGE_END_POINT = "/calculator/rakeDatabase";
    public static String HOST = "http://localhost:8080";
    public static String HOMEPAGE = "/";

    public static String valueOf(String nameOfField) {
        try {
            String field;
            EndPoints endPoints = new EndPoints();
            field = endPoints.getClass().getDeclaredField(nameOfField).get(endPoints).toString();
            return field;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
