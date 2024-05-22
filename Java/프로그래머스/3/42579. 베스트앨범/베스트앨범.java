import java.util.*;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        Map<String, Integer> genreCount = new HashMap<>();
        Map<String, List<List<Integer>>> genreIndex = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int count = plays[i];
            if (genreCount.get(genre) == null) {
                genreCount.put(genre, 0);
                genreIndex.put(genre, new ArrayList<>());
            }
            
            List<Integer> genreData = new ArrayList<>();            
            genreData.add(i);
            genreData.add(count);        
            
            genreCount.put(genre, genreCount.get(genre) + count);
            genreIndex.get(genre).add(genreData);
        }
        
        List<String> genreOrder = new ArrayList(genreCount.keySet());
        Collections.sort(genreOrder, (o1, o2) -> {
            return genreCount.get(o2) - genreCount.get(o1);
        });
        
        List<Integer> answerList = new ArrayList<>();
        
        for (String genre : genreOrder) {
            List<List<Integer>> genreList = genreIndex.get(genre);
            
            genreList.sort((o1, o2) -> {
                if (o2.get(1) == o1.get(1)) {
                    return o1.get(0) - o2.get(0);
                }
                
                return o2.get(1) - o1.get(1);
            });
            
            answerList.add(genreList.get(0).get(0));
            if(genreList.size() > 1)
                answerList.add(genreList.get(1).get(0));
        }
        
        
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}