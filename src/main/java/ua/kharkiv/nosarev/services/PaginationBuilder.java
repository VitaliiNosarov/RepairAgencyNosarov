package ua.kharkiv.nosarev.services;

import ua.kharkiv.nosarev.entitie.PaginationObject;

import javax.servlet.http.HttpServletRequest;

public class PaginationBuilder {

    private int currentPage;
    private int recordsPerPage;
    private int startPosition;
    private String orderBy;
    private String isReverse;
    private String filter;
    private String filterParam;
    private String sqlQuery;

    private PaginationBuilder(int currentPage, int recordsPerPage) {
        this.currentPage = currentPage;
        this.recordsPerPage = recordsPerPage;
        startPosition = currentPage * recordsPerPage - recordsPerPage;
    }


    public PaginationObject getPgObjectFromRequest(HttpServletRequest req) {
        PaginationObject pagObject;

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
            builder = new PaginationBuilder(curPage, recPerPage);
        }

        public Builder addFilter(String filter) {
            PaginationBuilder.this.filter = filter;
            return this;
        }

        public Builder addFilterParam(String filterParam) {
            PaginationBuilder.this.filterParam = filterParam;
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