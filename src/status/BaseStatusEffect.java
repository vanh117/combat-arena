package status;

public abstract class BaseStatusEffect implements IStatusEffect {
    protected int duration;
    
    public BaseStatusEffect(int duration) { 
        this.duration = duration; 
    }
    
    public void tick() { 
        if (duration > 0) duration--; 
    }
    
    public boolean isExpired() { 
        return duration <= 0; 
    }
}