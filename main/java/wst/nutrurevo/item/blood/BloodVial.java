package wst.nutrurevo.item.blood;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
final class BloodVialPro extends Properties{
	public BloodVialPro(){
		this.maxStackSize(16);
	}
}

public class BloodVial extends Item{
	
	public BloodVial() {
		super(new BloodVialPro());
	}
	
}
