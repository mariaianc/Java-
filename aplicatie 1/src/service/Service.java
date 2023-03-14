package service;

import domain.Document;
import repository.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Document> getDocumentsSortedAfterName()
    {
        return repo.getDocuments()
                .stream()
                .sorted
                        ((a,b)->a.getName().compareTo(b.getName()))
                .collect(Collectors.toCollection(ArrayList::new));  //asa se face arraylist inapoi pt ca cu stream le transformi in stream
    }

    public ArrayList<Document> filterOnSearchedWords(String words)
    {
        return repo.getDocuments()
                .stream()
                .filter(document -> (document.getName().contains(words) || document.getKeyWords().contains(words)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void updateDB(Document document,String new_keys,String new_content)
    {
        this.repo.updateDB(document.getId(), document.getKeyWords(),document.getContent());
        this.repo.readFromDB();  //daca pun asta le afisaza de doua ori si nu face update
        //return repo.getDocuments();
    }

}
