<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

      <div class="container-md">
        <div class="container-md d-flex justify-content-between align-items-center">
          <h1>CAEN PROTON RFID READER CONTROL CENTER</h1>
          <nav>
            <div>
              <a href="<spring:url value="/" />">Home</a>
            </div>
            <div class="sub">
              <a href="<spring:url value="/config" />">Configuration</a>
            </div>
            <div class="sub">
              <a href="<spring:url value="/addepcs" />">Write Epcs</a>
            </div>
            <div class="sub">
              <a href="<spring:url value="/inventory" />">TBA-Inventory operation</a>
            </div>
          </nav>
        </div>
        <div width="100%" style="height: 3em;" class="container-md d-flex justify-content-end align-items-center">
        </div>
      </div>
