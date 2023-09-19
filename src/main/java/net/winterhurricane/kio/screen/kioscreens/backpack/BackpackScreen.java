package net.winterhurricane.kio.screen.kioscreens.backpack;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.winterhurricane.kio.screen.kioscreenhandlers.backpack.BackpackScreenHandler;

public class BackpackScreen extends HandledScreen<BackpackScreenHandler> {
    private static final Identifier GENERIC_TEXTURE =
            new Identifier("kio", "textures/gui/external_inventories/vanilla/generic_54.png");
    private static final Identifier TIER4_LEFT =
            new Identifier("kio", "textures/gui/backpack_slots/tier4_left.png");
    private static final Identifier TIER4_RIGHT =
            new Identifier("kio", "textures/gui/backpack_slots/tier4_right.png");
    private static final Identifier PLAYER_INVENTORY =
            new Identifier("kio", "textures/gui/backpack_inventories/backpack_inventory.png");
    private static final Identifier TOOLBELT =
            new Identifier("kio", "textures/gui/backpack_slots/tier4_toolbelt.png");


    public BackpackScreen(BackpackScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // Helper variables to easily translate relative coordinates for different screen components
    int containerHeight = 133;
    int inventoryHeight = 176;
    int inventoryWidth = 176;
    int toolbeltHeight = 47;
    int backpackWidth = 69;
    private long startTime = -1L;

    @Override
    protected void init() {
        backgroundHeight = containerHeight + inventoryHeight + toolbeltHeight;
        backgroundWidth = inventoryWidth + 2 * backpackWidth;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(GENERIC_TEXTURE, x + backpackWidth, y, 0, 0, inventoryWidth, containerHeight);
        context.drawTexture(PLAYER_INVENTORY, x + backpackWidth, y + containerHeight, 0, 0, inventoryWidth, inventoryHeight);
        context.drawTexture(TOOLBELT, x + backpackWidth, y + containerHeight + inventoryHeight, 0, 0, inventoryWidth, toolbeltHeight);

        long l = Util.getMeasuringTimeMs();
        if (this.startTime == -1L) {
            this.startTime = l;
        }
        DrawContext leftSlotsAnim = context;
        leftSlotsAnim.drawTexture(TIER4_LEFT, x, y + containerHeight, 0, 0, backpackWidth, inventoryHeight);
        if (l - this.startTime <= 600L) {
            leftSlotsAnim.getMatrices().push();
            leftSlotsAnim.getMatrices().translate((width - backgroundWidth)/2 + (l - this.startTime) / 50, 0, 0);
        } else {
            this.startTime = -2L;
        }
        DrawContext rightSlotsAnim = context;
        rightSlotsAnim.drawTexture(TIER4_RIGHT, x + inventoryWidth + backpackWidth, y + containerHeight, 0, 0, backpackWidth,
                inventoryHeight);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0x404040, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    // Copied from the ToastManager class to learn animation handling
    /*
    public boolean draw(int x, DrawContext context) {
        long l = Util.getMeasuringTimeMs();
        if (this.startTime == -1L) {
            this.startTime = l;
            this.visibility.playSound(ToastManager.this.client.getSoundManager());
        }

        if (this.visibility == Toast.Visibility.SHOW && l - this.startTime <= 600L) {
            this.showTime = l;
        }

        context.getMatrices().push();
        context.getMatrices().translate((float)x - (float)this.instance.getWidth() * this.getDisappearProgress(l), (float)(this.topIndex * 32), 800.0F);
        Toast.Visibility visibility = this.instance.draw(context, ToastManager.this, l - this.showTime);
        context.getMatrices().pop();
        if (visibility != this.visibility) {
            this.startTime = l - (long)((int)((1.0F - this.getDisappearProgress(l)) * 600.0F));
            this.visibility = visibility;
            this.visibility.playSound(ToastManager.this.client.getSoundManager());
        }

        return this.visibility == Toast.Visibility.HIDE && l - this.startTime > 600L;
    }
    */
}
