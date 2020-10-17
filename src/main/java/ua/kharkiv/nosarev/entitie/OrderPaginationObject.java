package ua.kharkiv.nosarev.entitie;

import ua.kharkiv.nosarev.dao.SQLConstant;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;

public class OrderPaginationObject {


    public static final String GROUP_BY_ID = " GROUP BY id ";
    public static final String LIMIT = " LIMIT ?, ?";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String REVERSE = " DESC ";
    public static final String WHERE = " WHERE ";
    boolean reverse;

    private int currentPage;
    private int recordsPerPage;
    private int startPosition;
    private PaginationField orderBy;
    private PaginationField filter;
    private String filterParam;
    private StringBuilder sqlQuery;
    private StringBuilder filterQuery;
    private int numberOfPages;
    private int amountOfRows;

    private OrderPaginationObject(int currentPage, int recordsPerPage) {
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
        this.startPosition = currentPage * recordsPerPage - recordsPerPage;
        this.filterQuery = new StringBuilder();
        this.sqlQuery = new StringBuilder();
    }

    public boolean isReverse() {
        return reverse;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public void setAmountOfRows(int amountOfRows) {
        this.amountOfRows = amountOfRows;
    }

    public StringBuilder getFilterQuery() {
        return filterQuery;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public PaginationField getOrderBy() {
        return orderBy;
    }

    public PaginationField getFilter() {
        return filter;
    }

    public String getFilterParam() {
        return filterParam;
    }

    public StringBuilder getSqlQuery() {
        return sqlQuery;
    }

    public static class Builder {

        private OrderPaginationObject builderInstance;

        public Builder(int currentPage, int recordsPerPage) {
            builderInstance = new OrderPaginationObject(currentPage, recordsPerPage);
            builderInstance.sqlQuery.append(SQLConstant.FIND_ORDERS);
        }

        public Builder addFilter(String filter, String filterParam) {
            if (filter != null && filter.length() > 1) {
                builderInstance.filter = PaginationField.valueOf(filter);
                if (filterParam != null && filterParam.length() > 0) {
                    builderInstance.filterParam = filterParam;
                    builderInstance.filterQuery.append(WHERE)
                            .append(builderInstance.filter.getQuery())
                            .append(" = ?");
                    builderInstance.sqlQuery.append(builderInstance.filterQuery);
                }
            }
            return this;
        }

        public Builder addSorting(String orderBy, boolean reverse) {
            builderInstance.orderBy = PaginationField.valueOf(orderBy);
            builderInstance.sqlQuery.append(GROUP_BY_ID)
                    .append(ORDER_BY).append(builderInstance.orderBy.getQuery());
            if (builderInstance.reverse = reverse) {
                builderInstance.sqlQuery.append(REVERSE);
            }
            return this;
        }

        public Builder addLimit() {
            builderInstance.sqlQuery.append(LIMIT);
            return this;
        }

        public OrderPaginationObject build() {
            return builderInstance;
        }
    }
}