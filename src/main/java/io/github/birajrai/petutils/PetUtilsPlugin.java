package io.github.birajrai.petutils;

import io.github.birajrai.petutils.commands.ForceTransferPetCommand;
import io.github.birajrai.petutils.commands.TransferPetCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class PetUtilsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
//        Commands Over Here
        this.getCommand("transferpet").setExecutor(new TransferPetCommand());
        this.getCommand("forcetransferpet").setExecutor(new ForceTransferPetCommand());
    }

    @Override
    public void onDisable() {
    }
}