package eyeq.jarijarieating;

import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import eyeq.jarijarieating.event.JariJariEatingEventHandler;

import java.io.File;

import static eyeq.jarijarieating.JariJariEating.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class JariJariEating {
    public static final String MOD_ID = "eyeq_jarijarieating";

    @Mod.Instance(MOD_ID)
    public static JariJariEating instance;

    public static final String I18n_JARIJARI = "msg.jarijari.txt";

    public static float prob;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new JariJariEatingEventHandler());
        load(new Configuration(event.getSuggestedConfigurationFile()));
        if(event.getSide().isServer()) {
            return;
        }
        createFiles();
    }

    public static void load(Configuration config) {
        config.load();

        String category = "Float";
        prob = (float) config.get(category, "prob", 0.01).getDouble();

        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void createFiles() {
        File project = new File("../1.11.2-JariJariEating");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, I18n_JARIJARI, "I feel gravel ...");
        language.register(LanguageResourceManager.JA_JP, I18n_JARIJARI, "じゃりじゃりする……");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
