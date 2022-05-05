package com.syntech.repository;

import com.syntech.model.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bipan
 */
@Stateless
public class CategoryRepository extends AbstractRepository<Category> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public CategoryRepository() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    @Override
//    public void edit(Category category) {
//        try {
//            Connection con = establishConnection();
//            String sql = "UPDATE Category SET name = ?, totalMarks = ? WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, category.getName());
//            ps.setDouble(2, category.getTotalMarks());
//            ps.setLong(3, category.getId());
//            ps.executeUpdate();
//            ps.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
//
//    @Override
//    public void create(Category obj) {
//        try {
//            Connection con = establishConnection();
//            Statement stmt = con.createStatement();
//            String insert = "INSERT INTO Category (name,totalMarks) VALUES (?,?)";
//            PreparedStatement ps = con.prepareStatement(insert);
//            ps.setString(1, obj.getName());
//            ps.setDouble(2, obj.getTotalMarks());
//            ps.executeUpdate();
//            ResultSet rs = stmt.executeQuery("select * from Category");
//            while (rs.next()) {
//                System.out.println(rs.getString(2) + "  " + rs.getDouble(3));
//            }
//            ps.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    @Override
//    public void delete(Category obj) {
//        try {
//            Connection con = establishConnection();
//            String delete = "DELETE FROM Category WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(delete);
//            ps.setLong(1, obj.getId());
//            ps.executeUpdate();
//            ps.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    @Override
//    public List<Category> findAll() {
//        List<Category> list = new ArrayList<Category>();
//        try {
//            Connection con = establishConnection();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from Category");
//            while (rs.next()) {
//                Category category = new Category(rs.getLong(1), rs.getString(2), rs.getDouble(3));
//                list.add(category);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return list;
//    }
//
//    @Override
//    public Category findById(Long id) {
//        Category category = new Category();
//        try {
//            Connection con = establishConnection();
//            String query = "SELECT * FROM Category WHERE id = ?";
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setLong(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                category = new Category(rs.getLong(1), rs.getString(2), rs.getDouble(3));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return category;
//    }
}
