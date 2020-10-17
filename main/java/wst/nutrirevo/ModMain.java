package wst.nutrirevo;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wst.nutrirevo.system.BlockManager;
import wst.nutrurevo.item.blood.BloodVial;

@Mod(ModMain.MODID)
public class ModMain {
	public static final String MODID = "nutrirevo";
	private static final Logger LOGGER = LogManager.getLogger();
	
	
	public ModMain(){
		FMLJavaModLoadingContext fmlloadingcontext = FMLJavaModLoadingContext.get();
		fmlloadingcontext.getModEventBus().addListener(this::setup);
		fmlloadingcontext.getModEventBus().addListener(this::doClientStuff);
		fmlloadingcontext.getModEventBus().addListener(this::enqueueIMC);
		fmlloadingcontext.getModEventBus().addListener(this::processIMC);
	}
	
	 private void setup(final FMLCommonSetupEvent event)
	    {
	        // some preinit code
	        LOGGER.info("HELLO FROM PREINIT");
	        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	    }

	    private void doClientStuff(final FMLClientSetupEvent event) {
	        // do something that can only be done on the client
	        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
	    }

	    private void enqueueIMC(final InterModEnqueueEvent event)
	    {
	        // some example code to dispatch IMC to another mod
	        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	    }

	    private void processIMC(final InterModProcessEvent event)
	    {
	        // some example code to receive and process InterModComms from other mods
	        LOGGER.info("Got IMC {}", event.getIMCStream().
	                map(m->m.getMessageSupplier().get()).
	                collect(Collectors.toList()));
	    }
	    // You can use SubscribeEvent and let the Event Bus discover methods to call
	    @SubscribeEvent
	    public void onServerStarting(FMLServerStartingEvent event) {
	        // do something when the server starts
	        LOGGER.info("HELLO from server starting");
	    }

	    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	    // Event bus for receiving Registry Events)
	    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	    public static class RegistryEvents {
	    	private final static Block bckt = new BlockTT();
	        @SubscribeEvent
	        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
	            //blockRegistryEvent.getRegistry().register(bckt);
	            //BlockManager.get().RegisterAllBlock(blockRegistryEvent);
	        }
	        
	        @SubscribeEvent
	        public static void onItemsRegistry(final RegistryEvent.Register<Item> blockRegistryEvent) {
	        	Item bloodvial = new BloodVial().setRegistryName("bloodvial");
	        	blockRegistryEvent.getRegistry().register(bloodvial);
	        	Minecraft.getInstance().getItemRenderer().getItemModelMesher().register(bloodvial, new ModelResourceLocation(MODID + "bloodvial"));
	        }
	        
	    }
}
