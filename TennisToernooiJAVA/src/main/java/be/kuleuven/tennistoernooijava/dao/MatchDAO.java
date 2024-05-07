package be.kuleuven.tennistoernooijava.dao;

import be.kuleuven.tennistoernooijava.model.Match;

import java.util.List;

public class MatchDAO implements BaseDAO<Match, Integer> {
    @Override
    public Class<Match> getEntityClass() {
        return Match.class;
    }

    @Override
    public Match create(Match entity) {
        return BaseDAO.super.create(entity);
    }

    @Override
    public Match update(Match entity) {
        return BaseDAO.super.update(entity);
    }

    @Override
    public void delete(Match entity) {
        BaseDAO.super.delete(entity);
    }

    @Override
    public Match find(Integer integer) {
        return BaseDAO.super.find(integer);
    }

    @Override
    public List<Match> findAll() {
        return BaseDAO.super.findAll();
    }
}
