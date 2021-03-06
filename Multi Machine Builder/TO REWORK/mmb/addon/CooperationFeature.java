package mmb.addon;

import mmb.MODS.info.AddonCentral;
import mmb.recipes.RecipeMachine;
import mmb.recipes.RecipeType;

public interface CooperationFeature<A extends AddonCentral, B extends AddonCentral> {
	void addCompatibleRecipeMachine(RecipeType rt, RecipeMachine rm);
}
