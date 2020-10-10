package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;

public class PaginationObject {

    private int currentPage;
    private int recordsPerPage;
    private PaginationField orderBy;
    private boolean isReverse;
    private PaginationField filter;
    private String filterParam;


    public PaginationObject(int currentPage, int recordsPerPage, PaginationField orderBy) {
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
        this.orderBy = orderBy;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public PaginationField getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(PaginationField orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public PaginationField getFilter() {
        return filter;
    }

    public void setFilter(PaginationField filter) {
        this.filter = filter;
    }

    public String getFilterParam() {
        return filterParam;
    }

    public void setFilterParam(String filterParam) {
        this.filterParam = filterParam;
    }
}

