package com.revature.tribble.dao;

import com.revature.tribble.model.Tribble;
import com.revature.tribble.service.PrimaryKeyService;
import com.revature.tribble.util.ConnectionSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TribbleDao implements com.revature.tribble.dao.GenericDao<Tribble> {
    private ConnectionSession ses;

    public TribbleDao() {
        ses = new ConnectionSession();
    }

    @Override
    public List<Tribble> getList() {
        List<Tribble> result = new ArrayList<>();

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM tribble")) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tribble t =  new Tribble();
                t.setName(rs.getString("name"));
                t.setColor(rs.getString("color"));
                t.setMass(rs.getInt("mass"));
                t.setId(rs.getInt("id"));
                t.setLabId(rs.getInt("lab_id"));
                result.add(t);
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Tribble getById(int id) {
        Tribble result = new Tribble();
        boolean isFound = false;

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM tribble where (id=?)")) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                isFound=true;
                result.setName(rs.getString("name"));
                result.setColor(rs.getString("color"));
                result.setMass(rs.getInt("mass"));
                result.setId(rs.getInt("id"));
                result.setLabId(rs.getInt("lab_id"));
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        if (isFound) return result;
        else return null;
    }

    public List<Tribble> getByLabId(int labId) {
        List<Tribble> result = new ArrayList<>();

        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("SELECT * FROM tribble where (lab_id=?)")) {
            ps.setInt(1,labId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tribble t =  new Tribble();
                t.setName(rs.getString("name"));
                t.setColor(rs.getString("color"));
                t.setMass(rs.getInt("mass"));
                t.setId(rs.getInt("id"));
                t.setLabId(rs.getInt("lab_id"));
                result.add(t);
            }
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void insert(Tribble tribble) {
        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("INSERT INTO tribble (id, color, mass, 'name', lab_id) VALUES (?,?,?,?,?)")) {
            ps.setInt(1, PrimaryKeyService.newTribbleId());
            ps.setString(2,tribble.getColor());
            ps.setInt(3,tribble.getMass());
            ps.setString(4,tribble.getName());
            ps.setInt(5,tribble.getLabId());
            ps.executeUpdate();
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrUpdate(Tribble tribble) {
        if(getById(tribble.getId()) != null){
            try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("UPDATE tribble SET color=?, mass=?, 'name'=?, lab_id=? WHERE id=?;")) {
                ps.setString(1,tribble.getColor());
                ps.setInt(2,tribble.getMass());
                ps.setString(3,tribble.getName());
                ps.setInt(4,tribble.getLabId());
                ps.setInt(5,tribble.getId());
                ps.executeUpdate();
            } catch (ClassCastException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            insert(tribble);
        }
    }

    @Override
    public void delete(Tribble tribble) {
        try(PreparedStatement ps = ses.getActiveConnection().prepareStatement("DELETE FROM tribble WHERE id=?;")) {
            ps.setInt(1, tribble.getId());
            ps.executeUpdate();
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }
    }
}
