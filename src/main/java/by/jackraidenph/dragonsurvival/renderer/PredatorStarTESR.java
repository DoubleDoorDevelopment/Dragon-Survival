package by.jackraidenph.dragonsurvival.renderer;

import by.jackraidenph.dragonsurvival.DragonSurvivalMod;
import by.jackraidenph.dragonsurvival.shader.ModShaders;
import by.jackraidenph.dragonsurvival.tiles.PredatorStarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.ARBShaderObjects;

public class PredatorStarTESR extends TileEntityRenderer<PredatorStarTileEntity> {

    public static final Material CAGE_TEXTURE = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(DragonSurvivalMod.MODID,"te/star/cage"));
    public static final Material WIND_TEXTURE = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(DragonSurvivalMod.MODID, "te/star/wind"));
    public static final Material VERTICAL_WIND_TEXTURE = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(DragonSurvivalMod.MODID,"te/star/wind_vertical"));
    public static final Material OPEN_EYE_TEXTURE = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(DragonSurvivalMod.MODID,"te/star/open_eye"));
    private final ModelRenderer field_228872_h_ = new ModelRenderer(16, 16, 0, 0);
    private final ModelRenderer field_228873_i_;
    private final ModelRenderer field_228874_j_;
    private final ModelRenderer field_228875_k_;

    public PredatorStarTESR(TileEntityRendererDispatcher p_i226009_1_) {
        super(p_i226009_1_);
        this.field_228872_h_.addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.01F);
        this.field_228873_i_ = new ModelRenderer(64, 32, 0, 0);
        this.field_228873_i_.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F);
        this.field_228874_j_ = new ModelRenderer(32, 16, 0, 0);
        this.field_228874_j_.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F);
        this.field_228875_k_ = new ModelRenderer(32, 16, 0, 0);
        this.field_228875_k_.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
    }


    @Override
    public void render(PredatorStarTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float f = (float) tileEntityIn.getTicksExisted() + partialTicks;
        float f1 = tileEntityIn.getActiveRotation(partialTicks) * (180F / (float) Math.PI);
        float f2 = MathHelper.sin(f * 0.1F) / 2.0F + 0.5F;
        f2 = f2 * f2 + f2;
        matrixStackIn.push();
        matrixStackIn.translate(0.5D, (double) (0.3F + f2 * 0.2F), 0.5D);
        Vector3f vector3f = new Vector3f(0.5F, 1.0F, 0.5F);
        vector3f.normalize();
        matrixStackIn.rotate(new Quaternion(vector3f, f1, true));
        this.field_228875_k_.render(matrixStackIn, CAGE_TEXTURE.getBuffer(bufferIn, RenderType::getEntityTranslucent), combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
        int i = tileEntityIn.getTicksExisted() / 66 % 3;
        matrixStackIn.push();
        matrixStackIn.translate(0.5D, 0.5D, 0.5D);
        if (i == 1) {
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
        } else if (i == 2) {
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }

        IVertexBuilder ivertexbuilder = (i == 1 ? VERTICAL_WIND_TEXTURE : WIND_TEXTURE).getBuffer(bufferIn, RenderType::getEntityTranslucent);
        this.field_228873_i_.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
        matrixStackIn.push();
        matrixStackIn.translate(0.5D, 0.5D, 0.5D);
        matrixStackIn.scale(0.875F, 0.875F, 0.875F);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(180.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.0F));
        this.field_228873_i_.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
        ActiveRenderInfo activerenderinfo = this.renderDispatcher.renderInfo;
        matrixStackIn.push();
        matrixStackIn.translate(0.5D, (double) (0.3F + f2 * 0.2F), 0.5D);
        matrixStackIn.scale(0.5F, 0.5F, 0.5F);
        float f3 = -activerenderinfo.getYaw();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f3));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(activerenderinfo.getPitch()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.0F));
        float f4 = 1.3333334F;
        matrixStackIn.scale(1.3333334F, 1.3333334F, 1.3333334F);
        this.field_228872_h_.render(matrixStackIn, OPEN_EYE_TEXTURE.getBuffer(bufferIn, RenderType::getEntityTranslucent), combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
    }
}
