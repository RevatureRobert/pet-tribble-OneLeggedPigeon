package com.revature.tribble.dao;


import com.revature.tribble.model.Lab;
import com.revature.tribble.model.Tribble;
import com.revature.tribble.service.PrimaryKeyService;
import com.revature.tribble.util.ConnectionSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabDao implements com.revature.tribble.dao.GenericDao<Lab> {
    private ConnectionSession ses;
    private TribbleDao tribbleDao;

    public LabDao() {
        ses = new ConnectionSession();
        tribbleDao = new TribbleDao();
    }

    @Override
    public List<Lab> getList() {
        List<Lab> result = new ArrayList<>();

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM lab")) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Lab l =  new Lab();
                l.setName(rs.getString("l_name"));
                int resultId = rs.getInt("id");
                l.setId(resultId);
                l.setTribbles(tribbleDao.getByLabId(resultId));
                result.add(l);
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Lab getById(int id) {
        Lab result = new Lab();
        boolean isFound = false;

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM lab where (id=?)")) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                isFound=true;
                result.setName(rs.getString("l_name"));
                int resultId = rs.getInt("id");
                result.setId(resultId);
                result.setTribbles(tribbleDao.getByLabId(resultId));
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        if (isFound) return result;
        else return null;
    }

    @Override
    public void insert(Lab lab) {
        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("INSERT INTO lab (id, l_name) VALUES (?,?)")) {
            ps.setInt(1, PrimaryKeyService.newLabId());
            ps.setString(2,lab.getName());
            ps.executeUpdate();
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
        for(Tribble t : lab.getTribbles()) {
            tribbleDao.insert(t);
        }
    }

    @Override
    public void insertOrUpdate(Lab lab) {
        if(getById(lab.getId()) != null){
            try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("UPDATE lab l_name=? WHERE id=?;")) {
                ps.setString(1,lab.getName());
                ps.setInt(2,lab.getId());
                ps.executeUpdate();
            } catch (ClassCastException | SQLException e) {
                e.printStackTrace();
            }
            for(Tribble t : lab.getTribbles()) {
                tribbleDao.insertOrUpdate(t);
            }
        } else {
            insert(lab);
        }
    }

    @Override
    public void delete(Lab lab) {
        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("DELETE FROM lab WHERE id=?;")) {
            ps.setInt(1, lab.getId());
            ps.executeUpdate();
            // I'm allowing orphaned Tribbles
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
    }
}
