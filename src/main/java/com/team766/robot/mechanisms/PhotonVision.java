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
    //return a list of x,y,z, and angle from transform3d
    public List<Double> getXYZAngle(){
        List<Double> xyz = new ArrayList<Double>();
        xyz.add(getTransform3d().getTranslation().getX());
        xyz.add(getTransform3d().getTranslation().getY());
        xyz.add(getTransform3d().getTranslation().getZ());
        xyz.add(getTransform3d().getRotation().getAngle());
        return xyz;
    }

}
