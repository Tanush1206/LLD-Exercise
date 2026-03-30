public interface Storeinvoice {
    void save(String invId , String content);
    int countLines(String invId);
} 
