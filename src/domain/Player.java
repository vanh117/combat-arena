package domain;

import item.IItem;
import action.IAction;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Combatant {
    protected List<IItem> inventory;
    protected int skillCooldown;

    public Player(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
        this.inventory = new ArrayList<>();
        this.skillCooldown = 0;
    }

    public List<IItem> getInventory() { return inventory; }
    public void addItem(IItem item) { inventory.add(item); }
    public void removeItem(IItem item) { inventory.remove(item); }
    
    public int getSkillCooldown() { return skillCooldown; }
    public void setSkillCooldown(int cooldown) { this.skillCooldown = cooldown; }
    public void decreaseCooldown() {
        if (skillCooldown > 0) skillCooldown--;
    }

    public abstract String getSkillName();
    public abstract IAction getSpecialSkill();
}