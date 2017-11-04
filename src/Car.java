public class Car {
    double speed;
    final double MAX_SPEED = 65;
    final double MIN_SPEED = 1;
    int distanceInCurrBox;
    public Car()
    {
        
    }
    public double getSpeed()
    {
        return speed;
    }
    public void adjustSpeed(int change)
    {
        if(speed + change >= MIN_SPEED && speed + change <= MAX_SPEED)
        {
            speed += change;
        }
    }
    public int getDistanceInCurrBox()
    {
        return distanceInCurrBox;
    }
    public void setDistanceInCurrBox(int dist)
    {
        distanceInCurrBox = dist;
    }
}
