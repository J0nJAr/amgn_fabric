package jonjar.amgn.element.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import jonjar.amgn.Amgn;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PulverizerScreen extends HandledScreen<ScreenHandler> {
    //You can replace the background with whatever you like, just remember there will always be the recipe book button
    private static final Identifier BACKGROUND = new Identifier(Amgn.MODID,"textures/gui/container/pulverizer_interface.png");
    private boolean narrow;

    public PulverizerScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public void init() {
        super.init();
        this.narrow = this.width < 379;
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.drawBackground(matrices, delta, mouseX, mouseY);
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(BACKGROUND);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        int i = this.x;
        int j = this.y;

        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int l;
        if (((PulverizerScreenHandler)this.handler).isBurning()) {
            l = ((PulverizerScreenHandler)this.handler).getFuelProgress();
            this.drawTexture(matrices, i + 57, j + 36 + 12 - l, 176, 12 - l, 14, l + 1);
        }

        l = ((PulverizerScreenHandler)this.handler).getCookProgress();
        this.drawTexture(matrices, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private PulverizerScreenHandler getThisHandler() {
        return ((PulverizerScreenHandler)this.handler);
    }
}