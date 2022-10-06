package src;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Simulate {
    private String status;
    private int time;
    private String totalDistance;
    private int failRequest;
}
