package com.team766.robot.mechanisms;

import com.team766.framework.Mechanism;
import com.team766.hal.MotorController;
import com. team766.hal.RobotProvider;
import edu.wpi.first.math.geometry.Transform3d;
import org.photonvision.*;
import org.photonvision.targeting.PhotonTrackedTarget;
import java.util.*;

public class PhotonVision extends Mechanism {	
    PhotonCamera camera;
    List<PhotonTrackedTarget> targets;
    PhotonTrackedTarget target;

    public PhotonVision(){
        camera = new PhotonCamera("photonvision");
        var result = camera.getLatestResult();
        if(result.hasTargets()){
            targets = result.getTargets();
            target = result.getBestTarget();
        }

    }
    public void update(){
        var result = camera.getLatestResult();
        if(result.hasTargets()){
            targets = result.getTargets();
            target = result.getBestTarget();
        }
    }
    public int getID (){
        return target.getFiducialId();
    }
    public Transform3d getTransform3d(){
        return target.getBestCameraToTarget();
    }

}
