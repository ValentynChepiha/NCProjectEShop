package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces;

public interface ModelUserRoleRepository<T> extends ModelRepository<T> {

    String getOneOnlyAuthority(long id);

}
