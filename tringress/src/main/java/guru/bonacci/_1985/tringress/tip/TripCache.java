package guru.bonacci._1985.tringress.tip;

// TRIP stands for 'trans in progress'
public interface TripCache {
  
  public Boolean lock(String lockId);
}