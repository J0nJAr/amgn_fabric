package jonjar.amgn.mixin;

import jonjar.amgn.element.screen.StuffToggleGUI;
import jonjar.amgn.element.screen.StuffToggleScreen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.screen.options.*;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.client.gui.widget.OptionButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.network.packet.c2s.play.UpdateDifficultyC2SPacket;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OptionsScreen.class)
public abstract class OptionScreenMixin extends Screen {

    protected OptionScreenMixin(Text title){
        super(title);
    }

    @Shadow
    static Option[] OPTIONS;

    @Shadow
    Difficulty difficulty;

    @Shadow
    ButtonWidget difficultyButton;

    @Shadow
    private LockButtonWidget lockDifficultyButton;

    @Shadow
    @Final
    private Screen parent;

    @Shadow
    @Final
    private GameOptions settings;

    @Shadow
    protected abstract Text getDifficultyButtonText(Difficulty difficulty);

    @Shadow
    protected abstract void lockDifficulty(boolean difficultyLocked);

    @Shadow
    protected abstract void refreshResourcePacks(ResourcePackManager resourcePackManager);

    /**
     * @author KimBepo
     * @reason Replace Code.
     */
    @Overwrite
    public void init() {

        if(this.client == null) return;

        int i = 0;
        Option[] var2 = OPTIONS;
        int var3 = var2.length;

        for (Option option : var2) {
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 - 12 + 24 * (i >> 1);
            this.addButton(option.createButton(this.client.options, j, k, 150));
            ++i;
        }

        if (this.client.world != null) {
            this.difficulty = this.client.world.getDifficulty();
            this.difficultyButton = (ButtonWidget) this.addButton(new ButtonWidget(this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.getDifficultyButtonText(this.difficulty), (button) -> {
                this.difficulty = Difficulty.byOrdinal(this.difficulty.getId() + 1);
                this.client.getNetworkHandler().sendPacket(new UpdateDifficultyC2SPacket(this.difficulty));
                this.difficultyButton.setMessage(this.getDifficultyButtonText(this.difficulty));
            }));
            if (this.client.isIntegratedServerRunning() && !this.client.world.getLevelProperties().isHardcore()) {
                this.difficultyButton.setWidth(this.difficultyButton.getWidth() - 20);
                this.lockDifficultyButton = (LockButtonWidget) this.addButton(new LockButtonWidget(this.difficultyButton.x + this.difficultyButton.getWidth(), this.difficultyButton.y, (button) -> {
                    this.client.openScreen(new ConfirmScreen(this::lockDifficulty, new TranslatableText("difficulty.lock.title"), new TranslatableText("difficulty.lock.question", new Object[]{new TranslatableText("options.difficulty." + this.client.world.getLevelProperties().getDifficulty().getName())})));
                }));
                this.lockDifficultyButton.setLocked(this.client.world.getLevelProperties().isDifficultyLocked());
                this.lockDifficultyButton.active = !this.lockDifficultyButton.isLocked();
                this.difficultyButton.active = !this.lockDifficultyButton.isLocked();
            } else {
                this.difficultyButton.active = false;
            }
        } else {
            this.addButton(new OptionButtonWidget(this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, Option.REALMS_NOTIFICATIONS, Option.REALMS_NOTIFICATIONS.getDisplayString(this.settings), (button) -> {
                Option.REALMS_NOTIFICATIONS.toggle(this.settings);
                this.settings.write();
                button.setMessage(Option.REALMS_NOTIFICATIONS.getDisplayString(this.settings));
            }));
        }

        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, new TranslatableText("options.skinCustomisation"), (button) -> {
            this.client.openScreen(new SkinOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, new TranslatableText("options.sounds"), (button) -> {
            this.client.openScreen(new SoundOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, new TranslatableText("options.video"), (button) -> {
            this.client.openScreen(new VideoOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, new TranslatableText("options.controls"), (button) -> {
            this.client.openScreen(new ControlsOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, new TranslatableText("options.language"), (button) -> {
            this.client.openScreen(new LanguageOptionsScreen(this, this.settings, this.client.getLanguageManager()));
        }));
        this.addButton(new ButtonWidget(this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, new TranslatableText("options.chat.title"), (button) -> {
            this.client.openScreen(new ChatOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, new TranslatableText("options.resourcepack"), (button) -> {
            this.client.openScreen(new PackScreen(this, this.client.getResourcePackManager(), this::refreshResourcePacks, this.client.getResourcePackDir(), new TranslatableText("resourcePack.title")));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, new TranslatableText("options.amgn"), (button) -> {
            this.client.openScreen(new StuffToggleScreen(new StuffToggleGUI()));
        }));

        this.addButton(new ButtonWidget(this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, new TranslatableText("options.accessibility.title"), (button) -> {
            this.client.openScreen(new AccessibilityOptionsScreen(this, this.settings));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

}
