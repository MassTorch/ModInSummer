package wst.nutrirevo.system;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public class BlockManager {
	
	class BlockSheet{
		public BlockSheet(Block bck,String name){
			this.bck = bck;
			this.registryName = name;
		}
		Block bck;
		String registryName;
	}
	
	private static BlockManager bm = null;
	private ArrayList<BlockSheet> blockarray = null; 
	
	
	private BlockManager() {
		blockarray = new ArrayList<BlockSheet>();
	}
	
	public static BlockManager get() {
		if(bm == null) {
			bm = new BlockManager();
		}
		return bm;
	}
	
	public void addBlock(Block bck,String name){
		blockarray.add(new BlockSheet(bck,name));
	}
	
	public void RegisterAllBlock(RegistryEvent.Register<Block> blockRegistryEvent){
		for(BlockSheet bs : blockarray) {
			blockRegistryEvent.getRegistry().registerAll(bs.bck.setRegistryName(bs.registryName));
		}
	}	
}
