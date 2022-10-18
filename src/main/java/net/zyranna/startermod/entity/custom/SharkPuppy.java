package net.zyranna.startermod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.zyranna.startermod.entity.ModEntityTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import java.util.UUID;

public class SharkPuppy extends TamableAnimal implements IAnimatable {





   private  AnimationFactory factory = new AnimationFactory(this); //create animation factory

    public SharkPuppy(final EntityType<? extends SharkPuppy> entityType, final Level level) {//shark puppy builder
        super(entityType, level);



    }

    public static AttributeSupplier setAttributes(){  //shark puppy set attributes
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.ATTACK_DAMAGE,4.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.FOLLOW_RANGE,22.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }



    @Override
    protected void registerGoals() {  //shark puppy register goals

        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this,1.2D,false));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 5.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5,new WaterAvoidingRandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7,new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new NonTameRandomTargetGoal<>(this, LivingEntity.class, true, target -> target instanceof Chicken || target instanceof Cod || target instanceof Salmon|| target instanceof TropicalFish));
    }
    public boolean canBreatheUnderwater() {
        return true;
    } // allow shark puppy to not drown


    public boolean canBeLeashed(Player p_149122_) {
        return true;
    } // self explanatory


    public InteractionResult playerInteraction(Player player, InteractionHand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        final InteractionResult success = InteractionResult.sidedSuccess(level.isClientSide);
        Item item = itemstack.getItem();
        if (!isTame() && item == Items.BONE) // check if the shark puppy is wild and interaction item is tame item
        {
            itemstack.shrink(1); //reduce stack of interaction item by 1
            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) { //on random chance,
                this.tame(player);// tame shark puppy
                getMaxHealth();
                this.spawnTamingParticles(true); //show those darn tame hearts
            }else {
                this.spawnTamingParticles(false); //show that tame attempted but did not succeed
            }
            this.gameEvent(GameEvent.EAT, this);
            return success;
        }
        if (isTame() && isBreedingItem(itemstack)) // check if interact is with a breeding item
        {
            if (!level.isClientSide && canFallInLove() && getAge() == 0)
            {
                setInLove(player);
                itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        if (isOwnedBy(player))
        {
            if (player.isShiftKeyDown()) // shift right click to make sit
            {
                setOrderedToSit(!isInSittingPose());
                return success;
            }
        }
        return InteractionResult.PASS;
    }
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) //call the mob interact method above
    {
        ItemStack stack = player.getItemInHand(hand);
        InteractionResult result = stack.interactLivingEntity(player, this, hand);
        if (!result.consumesAction()) result = playerInteraction(player, hand);
        return result;
    }







    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sharkpup.walk", true));
            return PlayState.CONTINUE;
        }


        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sharkpup.idle",true));
        return  PlayState.CONTINUE;
    }


    public boolean wantsToAttack(LivingEntity entity, LivingEntity entity1) {  // filter out all of the things that the shark puppy should not attack
        if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
            if (entity instanceof SharkPuppy) {
                SharkPuppy puppy = (SharkPuppy)entity;
                return !puppy.isTame() || puppy.getOwner() != entity1;
            } else if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
                return false;
            } else if (entity instanceof AbstractHorse && ((AbstractHorse)entity).isTamed()) {
                return false;
            } else {
                return !(entity instanceof TamableAnimal) || !((TamableAnimal)entity).isTame();
            }
        } else {
            return false;
        }
    }




    protected void  playStepSound(BlockPos pos, BlockState blockState){  //set walk sound
        this.playSound(SoundEvents.TROPICAL_FISH_FLOP,0.15f,1.0f);
    }
    protected SoundEvent getAmbientSound(){
        return SoundEvents.AXOLOTL_IDLE_WATER;
    } //set ambient sound
    protected SoundEvent getHurtSound(DamageSource damageSource){
        return  SoundEvents.AXOLOTL_HURT;
    }  //set hurt noise
    protected SoundEvent getDeathSound(){
        return SoundEvents.AXOLOTL_DEATH;
    } // set death noise
    protected float getSoundVolume(){ return 0.2f;} // set volume of bean

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) { // breeding options
        SharkPuppy sharkPuppy = ModEntityTypes.SHARKPUP.get().create(level);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            sharkPuppy.setOwnerUUID(uuid);
            sharkPuppy.setTame(true);
        }
        return sharkPuppy;
    }

    public boolean isFood(ItemStack p_30440_) { // set edible items
        Item item = p_30440_.getItem();
        return item.isEdible() && p_30440_.getFoodProperties(this).isMeat();
    }

    public boolean isBreedingItem(ItemStack stack) //set breeding item
    {
        return stack.getItem() == Items.PRISMARINE_CRYSTALS;
    }






        // the following are manditory for entities
    @Override
    public  void  registerControllers(AnimationData data){
        data.addAnimationController(new AnimationController<>(this,"controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory(){

        return factory;
    }

}
