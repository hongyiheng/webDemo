package com.hyh.dang.entity;

import java.util.ArrayList;
import java.util.List;

public class JijinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JijinExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andNumIsNull() {
            addCriterion("Num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("Num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("Num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("Num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("Num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("Num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("Num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("Num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("Num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("Num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("Num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("Num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreatedtIsNull() {
            addCriterion("CreateDT is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtIsNotNull() {
            addCriterion("CreateDT is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtEqualTo(String value) {
            addCriterion("CreateDT =", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtNotEqualTo(String value) {
            addCriterion("CreateDT <>", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtGreaterThan(String value) {
            addCriterion("CreateDT >", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtGreaterThanOrEqualTo(String value) {
            addCriterion("CreateDT >=", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtLessThan(String value) {
            addCriterion("CreateDT <", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtLessThanOrEqualTo(String value) {
            addCriterion("CreateDT <=", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtLike(String value) {
            addCriterion("CreateDT like", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtNotLike(String value) {
            addCriterion("CreateDT not like", value, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtIn(List<String> values) {
            addCriterion("CreateDT in", values, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtNotIn(List<String> values) {
            addCriterion("CreateDT not in", values, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtBetween(String value1, String value2) {
            addCriterion("CreateDT between", value1, value2, "createdt");
            return (Criteria) this;
        }

        public Criteria andCreatedtNotBetween(String value1, String value2) {
            addCriterion("CreateDT not between", value1, value2, "createdt");
            return (Criteria) this;
        }

        public Criteria andRaiseIsNull() {
            addCriterion("Raise is null");
            return (Criteria) this;
        }

        public Criteria andRaiseIsNotNull() {
            addCriterion("Raise is not null");
            return (Criteria) this;
        }

        public Criteria andRaiseEqualTo(String value) {
            addCriterion("Raise =", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseNotEqualTo(String value) {
            addCriterion("Raise <>", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseGreaterThan(String value) {
            addCriterion("Raise >", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseGreaterThanOrEqualTo(String value) {
            addCriterion("Raise >=", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseLessThan(String value) {
            addCriterion("Raise <", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseLessThanOrEqualTo(String value) {
            addCriterion("Raise <=", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseLike(String value) {
            addCriterion("Raise like", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseNotLike(String value) {
            addCriterion("Raise not like", value, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseIn(List<String> values) {
            addCriterion("Raise in", values, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseNotIn(List<String> values) {
            addCriterion("Raise not in", values, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseBetween(String value1, String value2) {
            addCriterion("Raise between", value1, value2, "raise");
            return (Criteria) this;
        }

        public Criteria andRaiseNotBetween(String value1, String value2) {
            addCriterion("Raise not between", value1, value2, "raise");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}