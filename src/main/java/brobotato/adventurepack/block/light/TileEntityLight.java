package brobotato.adventurepack.block.light;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityLight extends TileEntity implements ITickable {

    private final int INVALID_VALUE = -1;

    private int timeLeft = INVALID_VALUE;

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {
        super.writeToNBT(parentNBTTagCompound);

        parentNBTTagCompound.setInteger("ticksLeft", timeLeft);

        return parentNBTTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound parentNBTTagCompound) {
        super.readFromNBT(parentNBTTagCompound);

        final int NBT_INT_ID = 3;
        int readTicks = INVALID_VALUE;
        if (parentNBTTagCompound.hasKey("ticksLeft", NBT_INT_ID)) {
            readTicks = parentNBTTagCompound.getInteger("ticksLeft");
            if (readTicks < 0) readTicks = INVALID_VALUE;
        }
        timeLeft = readTicks;
    }

    @Override
    public void update() {
        if (!this.hasWorld()) return;
        World world = this.getWorld();
        if (world.isRemote) return;
        if (timeLeft == INVALID_VALUE) return;
        --timeLeft;
        if (timeLeft > 0) return;
        world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
    }


    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
