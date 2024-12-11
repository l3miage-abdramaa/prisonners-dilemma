import java.util.List;

public class ToujoursCoopererStrategie implements StrategieInterface {
    @Override
    public String getCoup(List<String> coupsJoues) {
        return "COOPERER";
    }
}
