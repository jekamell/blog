<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
    <legend>Add topic</legend>

    <sf:form method="POST" modelAttribute="topic" role="form" cssClass="form-horizontal">
        <div class="form-group">
            <label for="topic-title" class="col-sm-2 control-label">Title</label>
            <div class="col-sm-10">
                <sf:input path="title" id="topic-title" class="form-control" placeholder="Title"/>
                <sf:errors path="title" cssClass="text-danger"  />
            </div>
        </div>

        <div class="form-group">
            <label for="topic-content" class="col-sm-2 control-label">Content</label>
            <div class="col-sm-10">
                <sf:textarea path="content" id="topic-content" class="form-control" placeholder="Content"/>
                <sf:errors path="content" cssClass="text-danger"  />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" value="Register" class="btn btn-primary "/>
            </div>
        </div>
    </sf:form>
</div>