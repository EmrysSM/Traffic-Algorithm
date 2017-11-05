import java.util.ArrayList;
public class bBox {
    ArrayList<Car> cars;
    int distance;
    int maxNum;
    public bBox(int dist)
    {
        maxNum = 1000;
        cars = new ArrayList<>();
        distance = dist;
    }
    public boolean addCar(int count)
    {
        if(cars.size() < maxNum)
        {
            cars.add(new Car(28, count));
            return true;
        }
        return false;
    }
    public boolean addCar(Car c)
    {
        if(cars.size() < maxNum)
        {
            cars.add(c);
            return true;
        }
        return false;
    }
    public double getAverageSpeed()
    {
        double averageSpeed = 0;
        for(Car c : cars)
        {
            averageSpeed += c.getSpeed();
        }
        return averageSpeed/cars.size();
    }
    public int numCars()
    {
        return cars.size();
    }
    public int getDistance()
    {
        return distance;
    }
    public ArrayList<Car> getCars()
    {
        return cars;
    }
}
