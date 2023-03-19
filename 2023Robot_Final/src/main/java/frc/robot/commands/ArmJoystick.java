package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArmJoystick extends CommandBase {
    private final Arm arm;
    private final DoubleSupplier setUpperMotor, setLowerMotor, setLowerMotor2;
    private final BooleanSupplier windowMotorForward, windowMotorBackward;

    public ArmJoystick(Arm arm, DoubleSupplier setUpperMotor, DoubleSupplier setLowerMotor,
            DoubleSupplier setLowerMotor2, BooleanSupplier windowMotorForward, BooleanSupplier windowMotorBackward) {
        this.arm = arm;
        this.setUpperMotor = setUpperMotor;
        this.setLowerMotor = setLowerMotor;
        this.setLowerMotor2 = setLowerMotor2;
        this.windowMotorForward = windowMotorForward;
        this.windowMotorBackward = windowMotorBackward;

        addRequirements(arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double upperPower = -setUpperMotor.getAsDouble();
        double lowerPower = (-setLowerMotor.getAsDouble() - setLowerMotor2.getAsDouble());
        //double windowPower = setWindowMotor.getAsDouble();

        arm.setUpperMotor(upperPower);
        arm.setLowerMotor(lowerPower);
        //arm.windowMotorForward(windowMotorForward);
        //arm.windowMotorBackward(windowMotorBackward);
        arm.moveWindowMotor(windowMotorForward, windowMotorBackward);

        // arm.toggleClaw(clawPos);
    }

    @Override
    public void end(boolean interrupted) {
        arm.setUpperMotor(0);
        arm.setLowerMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
