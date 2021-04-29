package com.revature.tribble.dao;


import com.revature.tribble.model.Lab;
import com.revature.tribble.service.PrimaryKeyService;
import com.revature.tribble.util.ConnectionSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabDao implements com.revature.tribble.dao.GenericDao<Lab> {
    private ConnectionSession ses;

    public LabDao() {
        ses = new ConnectionSession();
    }

    @Override
    public List<Lab> getList() {
        List<Lab> result = new ArrayList<>();

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM lab")) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Lab l =  new Lab();
                l.setName(rs.getString("name"));
                l.setId(rs.getInt("id"));
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
                result.setName(rs.getString("name"));
                result.setId(rs.getInt("id"));
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        if (isFound) return result;
        else return null;
    }

    @Override
    public void insert(Lab lab) {
        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("INSERT INTO lab (id, 'name') VALUES (?,?)")) {
            ps.setInt(1, PrimaryKeyService.newLabId());
            ps.setString(2,lab.getName());
            ps.executeUpdate();
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(Lab lab) {
        if(getById(lab.getId()) != null){
            try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("UPDATE lab 'name'=? WHERE id=?;")) {
                ps.setString(1,lab.getName());
                ps.setInt(2,lab.getId());
                ps.executeUpdate();
            } catch (ClassCastException | SQLException e) {
                e.printStackTrace();
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
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
    }
}
