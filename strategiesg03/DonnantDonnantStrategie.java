import java.util.List;

public class DonnantDonnantStrategie implements StrategieInterface {
    @Override
    public String getCoup(List<String> coupsJoues) {
        if (coupsJoues != null) {
            return coupsJoues.get(coupsJoues.size()-1);
        }
        return "COOPERER";
    }
}
