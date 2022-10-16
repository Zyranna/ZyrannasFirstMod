package net.zyranna.startermod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;

public class SharkPuppy extends TamableAnimal implements IAnimatable, NeutralMob {


    private  AnimationFactory factory = new AnimationFactory(this);


    public SharkPuppy(final EntityType<? extends SharkPuppy> entityType, final Level level) {
        super(entityType, level);


    }


    public static AttributeSupplier setAttributes(){
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.ATTACK_DAMAGE,4.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this,1.2D,false));
        this.goalSelector.addGoal(3,new WaterAvoidingRandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(4,new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Chicken.class,true));
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source)
    {
        return source == DamageSource.DROWN || super.isInvulnerableTo(source);
    }
    public boolean canBeLeashed(Player p_149122_) {
        return true;
    }


    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sharkpup.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.sharkpup.idle",true));
        return  PlayState.CONTINUE;
    }









    @Override
    public  void  registerControllers(AnimationData data){
        data.addAnimationController(new AnimationController<>(this,"controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory(){

        return factory;
    }


    protected void  playStepSound(BlockPos pos, BlockState blockState){
        this.playSound(SoundEvents.TROPICAL_FISH_FLOP,0.15f,1.0f);
    }
    protected SoundEvent getAmbientSound(){
        return SoundEvents.AXOLOTL_IDLE_WATER;
    }
    protected SoundEvent getHurtSound(DamageSource damageSource){
        return  SoundEvents.AXOLOTL_HURT;
    }
    protected SoundEvent getDeathSound(){
        return SoundEvents.AXOLOTL_DEATH;
    }
    protected float getSoundVolume(){ return 0.2f;}


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_21672_) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}
