package com.gaseng.checklist.Repository;

import com.gaseng.checklist.domain.Checklist;

import javax.persistence.EntityManager;

public class ChecklistRepository {
    private final EntityManager em;

    public ChecklistRepository(EntityManager em) {
        this.em = em;
    }
    public Checklist create(Checklist checklist) {
        em.persist(checklist);
        return checklist;
    }

    public Checklist search(Long mem_id){
        Checklist search_chk = em.find(Checklist.class, mem_id);
        return search_chk;
    }

}
