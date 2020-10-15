package ua.kharkiv.nosarev.services;

import ua.kharkiv.nosarev.dao.SQLConstant;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;

import javax.servlet.http.HttpServletRequest;

public class PaginationBuilder {

    private int currentPage;
    private int recordsPerPage;
    private int startPosition;
    private String orderBy;
    private String isReverse;
    private PaginationField filter;
    private String filterParam;
    private StringBuilder sqlQuery;

    private PaginationBuilder(int currentPage, int recordsPerPage) {
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
        startPosition = currentPage * recordsPerPage - recordsPerPage;
    }


    public PaginationObject getPgObjectFromRequest(HttpServletRequest req) {
        PaginationObject pagObject;

        //     int startPosition = pagObject.getCurrentPage() * recordsPerPage - recordsPerPage;

        //  filterparam, start, records,
//        PaginationBuilder builder = PaginationBuilder.newBuilder(req.getParameter("currentPage"),
//                req.getParameter("recordsPerPage"))
//                .addOrderBy(req.getParameter("orderBy"))
//                .addFilter(req.getParameter("filter"))
//                .addFilterParam(req.getParameter("filterParam"))
//                .addReverse(req.getParameter("reverse")).build();

        return null;
    }

    class Builder {

        private PaginationBuilder builder;

        private Builder(String currentPage, String recordsPerPage) {
            int curPage = Integer.parseInt(currentPage);
            int recPerPage = Integer.parseInt(recordsPerPage);
            sqlQuery = new StringBuilder(SQLConstant.FIND_ORDERS);
            builder = new PaginationBuilder(curPage, recPerPage);
        }

        public Builder addFilter(String filter, String filterParam) {
            if (filterParam != null && filterParam.length() > 0) {
                PaginationBuilder.this.filterParam = filterParam;
                if (filter != null && filter.length() > 1) {
                    PaginationBuilder.this.filter = PaginationField.valueOf(filter);
                    sqlQuery.append(SQLConstant.WHERE).append(PaginationBuilder.this.filter.getQuery())
                            .append(" = ?");
                }
            }
            return this;
        }

        public Builder addOrderBy(String orderBy) {
            PaginationBuilder.this.orderBy = orderBy;
            return this;
        }

        public Builder addReverse(String isReverse) {
            PaginationBuilder.this.isReverse = isReverse;
            return this;
        }

        public PaginationBuilder build() {
            return PaginationBuilder.this;
        }
    }
}