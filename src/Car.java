public class Car {
    int speed;
    int count;
    final double MAX_SPEED = 100;
    final double MIN_SPEED = 1;
    int distanceInCurrBox;
    public Car(int s, int c)
    {
        speed = s;
        count = c;
    }
    public int getSpeed()
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
    public void addDistanceInCurrBox(int dist)
    {
        distanceInCurrBox += dist;
    }

    public int getCount() {
        return count;
    }
}
