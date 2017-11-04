import java.util.ArrayList;
public class bBox {
    ArrayList<Car> cars = new ArrayList();
    int distance;
    int maxNum;
    public bBox()
    {
        
    }
    public boolean addCar()
    {
        if(cars.size() < maxNum)
        {
            cars.add(new Car());
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
}
