package com.team766.robot.mechanisms;

import com.team766.framework.Mechanism;
import com.team766.hal.MotorController;
import com. team766.hal.RobotProvider;
import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import java.util.*;

public class PhotonVision extends Mechanism {	
    PhotonCamera camera;
    
    public PhotonVision(){
        camera = new PhotonCamera("photonvision");
        var result = camera.getLatestResult();
        List<PhotonTrackedTarget> targets = result.getTargets();
    }
}
