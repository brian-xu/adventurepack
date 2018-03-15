package brobotato.adventurepack.block.light;

import brobotato.adventurepack.block.BlockTileEntity;
import brobotato.adventurepack.config.ModConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockLight extends BlockTileEntity {

    public BlockLight() {
        super(Material.AIR, "light");
    }

    /*  probably don't need this for now
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityLight) {
            TileEntityLight tileEntityData = (TileEntityLight) tileentity;
        }
    }
    */

    @Nullable
    @Override
    public TileEntityLight createTileEntity(World world, IBlockState state) {
        return new TileEntityLight();
    }

    @Override
    public Class<TileEntityLight> getTileEntityClass() {
        return TileEntityLight.class;
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getLightValue(IBlockState state) {
        return Math.min(15, ModConfig.client.helmetPower);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(pos, pos);
    }

    @Override
    @Nullable
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return false;
    }

}
