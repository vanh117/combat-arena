package domain;

import status.IStatusEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    private List<IStatusEffect> statusEffects;

    public Combatant(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public boolean isAlive() { return hp > 0; }

    public void setDefense(int defense) { this.defense = defense; }
    public void addAttack(int amount) { this.attack += amount; }

    public void takeDamage(int damage) {
        this.hp = Math.max(0, this.hp - damage);
    }

    public void heal(int amount) {
        this.hp = Math.min(this.maxHp, this.hp + amount);
    }

    public void addStatusEffect(IStatusEffect effect) {
        statusEffects.add(effect);
        effect.onApply(this);
    }

    public void removeStatusEffect(IStatusEffect effect) {
        effect.onRemove(this);
        statusEffects.remove(effect);
    }

    public List<IStatusEffect> getStatusEffects() {
        return Collections.unmodifiableList(statusEffects);
    }

    public boolean isStunned() {
        return statusEffects.stream().anyMatch(e -> e.getName().equals("Stun"));
    }

    // it is also the job of the combatant to check which effect can modify incoming damage
    public int modifyIncomingDamage(Combatant attacker, int damage) {
        for (IStatusEffect effect : statusEffects) {
            damage = effect.modifyIncomingDamage(attacker, this, damage);
        }
        return damage;
    }

    public void tickEffects() {
        Iterator<IStatusEffect> it = statusEffects.iterator();
        while (it.hasNext()) {
            IStatusEffect effect = it.next();
            effect.tick();
            if (effect.isExpired()) {
                effect.onRemove(this);
                it.remove();
            }
        }
    }
}