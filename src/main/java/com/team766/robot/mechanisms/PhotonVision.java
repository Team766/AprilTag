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
        if(hasTarget()){
            targets = result.getTargets();
            target = result.getBestTarget();
        }
    }
    
    //check if there is a target
    public boolean hasTarget(){
        var result = camera.getLatestResult();
        return (result == null ? false : result.hasTargets());
    }

    //return best target
    public void update(){
        if(hasTarget()){
            targets = camera.getLatestResult().getTargets();
            target = camera.getLatestResult().getBestTarget();
        }
    }
    //return a list of all targets
    public List<PhotonTrackedTarget> getAllTargets(){
        return targets;
    }

    //return the id of the target
    public int getID(){
        return target.getFiducialId();
    }

    //return a list of x,y,z, and angle from transform3d
    public List<Double> getXYZAngle(){
        if(hasTarget()){
            update();
            Transform3d target3D = target.getBestCameraToTarget();
            List<Double> xyz = new ArrayList<Double>();
            xyz.add(target3D.getTranslation().getX());
            xyz.add(target3D.getTranslation().getY());
            xyz.add(target3D.getTranslation().getZ());
            xyz.add(target3D.getRotation().getAngle());
            return xyz;
        }
        return null;
    }

}
