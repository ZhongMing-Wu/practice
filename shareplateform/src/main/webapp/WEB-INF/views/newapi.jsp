

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
Created by IntelliJ IDEA.
User: DELL
Date: 2019/9/16
Time: 11:42
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
	  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>i5ting_ztree_toc:newapi</title>
		<link href="/api/resource/toc/style/github-bf51422f4bb36427d391e4b75a1daa083c2d840e.css" media="all" rel="stylesheet" type="text/css"/>
		<link href="/api/resource/toc/style/github2-d731afd4f624c99a4b19ad69f3083cd6d02b81d5.css" media="all" rel="stylesheet" type="text/css"/>
		<link href="/api/resource/toc/css/zTreeStyle/zTreeStyle.css" media="all" rel="stylesheet" type="text/css"/>
	  <style>
		pre {
		    counter-reset: line-numbering;
		    border: solid 1px #d9d9d9;
		    border-radius: 0;
		    background: #fff;
		    padding: 0;
		    line-height: 23px;
		    margin-bottom: 30px;
		    white-space: pre;
		    overflow-x: auto;
		    word-break: inherit;
		    word-wrap: inherit;
		}

		pre a::before {
		  content: counter(line-numbering);
		  counter-increment: line-numbering;
		  padding-right: 1em; /* space after numbers */
		  width: 25px;
		  text-align: right;
		  opacity: 0.7;
		  display: inline-block;
		  color: #aaa;
		  background: #eee;
		  margin-right: 16px;
		  padding: 2px 10px;
		  font-size: 13px;
		  -webkit-touch-callout: none;
		  -webkit-user-select: none;
		  -khtml-user-select: none;
		  -moz-user-select: none;
		  -ms-user-select: none;
		  user-select: none;
		}

		pre a:first-of-type::before {
		  padding-top: 10px;
		}

		pre a:last-of-type::before {
		  padding-bottom: 10px;
		}

		pre a:only-of-type::before {
		  padding: 10px;
		}

		.highlight { background-color: #ffffcc } /* RIGHT */
		</style>
  </head>
  <body>
	  <div>
				<div style='width:25%;'>
						<ul id="tree" class="ztree" style='width:100%'>

						</ul>
				</div>
        <div id='readme' style='width:70%;margin-left:20%;'>
          	<article class='markdown-body'>
            	<h1 id="1-">1. 登录接口</h1>
<h2 id="-">登录账号</h2>
<p><strong>URL</strong>：/api/login</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>name</td>
<td>是</td>
<td>字符串</td>
<td>用户名</td>
</tr>
<tr>
<td>password</td>
<td>是</td>
<td>字符串</td>
<td>用户密码</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;name&quot;:&quot;woodji&quot;,
    &quot;password&quot;:&quot;123&quot;
}
</code></pre>
<p>输出（当用户是临时用户时，会返回当前用户的注册状态）：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;status&quot;: 200,
    &quot;msg&quot;: &quot;登录成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 2,
        &quot;loginuser&quot;: &quot;2&quot;,
        &quot;name&quot;: &quot;woodji&quot;,
        &quot;password&quot;: &quot;&quot;,
        &quot;role&quot;: &quot;tempMember&quot;
    },
    &quot;token&quot;: &quot;eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwic3ViIjoibG9naW4iLCJhdWQiOiJ3b29kamkiLCJpYXQiOjE1NzA0MzExNzUsImV4cCI6MTU3MDQ1OTk3NX0.uk64XP6e8MZ4C3qpLy61fiCVgnLKPNCRdol6oL2hgpU&quot;,
    &quot;registerStage&quot;: 3
}
</code></pre>
<h2 id="-">更改密码</h2>
<p><strong>URL</strong>：/api/password</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>userName</td>
<td>是</td>
<td>字符串</td>
<td>用户名</td>
</tr>
<tr>
<td>oldPassword</td>
<td>是</td>
<td>字符串</td>
<td>旧密码</td>
</tr>
<tr>
<td>newPassword</td>
<td>是</td>
<td>字符串</td>
<td>新密码</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;userName&quot;:&quot;woodjie&quot;,
    &quot;oldPassword&quot;:&quot;123&quot;,
    &quot;newPassword&quot;:&quot;1234&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;status&quot;: 200,
    &quot;msg&quot;: &quot;修改完成！&quot;
}
</code></pre>
<h2 id="-token">更新token</h2>
<p><strong>URL</strong>：/api/update</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前注册者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;获取成功!&quot;,
    &quot;token&quot;: &quot;eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMCIsInN1YiI6ImxvZ2luIiwiYXVkIjoiaHVhbmcxIiwiaWF0IjoxNTcwNzE0NDk3LCJleHAiOjE1NzA3NDMyOTd9.6dPt38pOMgr1ya8DdmmLBJfAvJiSuEcf8sjgV5KJQPY&quot;
}
</code></pre>
<h1 id="2-">2. 分阶段注册接口</h1>
<h2 id="-">临时加盟商</h2>
<h3 id="-0-">注册阶段0：注册登录信息</h3>
<p><strong>URL</strong>：/api/member/register/stage0 </p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>name</td>
<td>是</td>
<td>字符串</td>
<td>注册用户名</td>
</tr>
<tr>
<td>password</td>
<td>是</td>
<td>字符串</td>
<td>注册用户密码</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;name&quot;:&quot;woodji&quot;,
    &quot;password&quot;:&quot;123&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;注册成功！&quot;,
    &quot;token&quot;: &quot;eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWdpc3RlciIsImF1ZCI6Indvb2RqaSIsImlhdCI6MTU3MDM3MDQ1MSwiZXhwIjoxNTcwMzk5MjUxfQ.82dluI0O_4CusWe0_6fkh3br3JEKWjHO4eBBHQdGayc&quot;
}
</code></pre>
<h3 id="-1-">注册阶段1：注册加盟商实体信息</h3>
<p><strong>URL</strong>：/api/member/register/stage1</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>memberName</td>
<td>是</td>
<td>字符串</td>
<td>会员名称</td>
</tr>
<tr>
<td>memberAbbr</td>
<td>是</td>
<td>字符串</td>
<td>会员简称</td>
</tr>
<tr>
<td>memberRegisterAddr</td>
<td>是</td>
<td>字符串</td>
<td>会员注册地址</td>
</tr>
<tr>
<td>memberAddr</td>
<td>是</td>
<td>字符串</td>
<td>会员运营地址</td>
</tr>
<tr>
<td>corporation</td>
<td>是</td>
<td>字符串</td>
<td>法定代表人</td>
</tr>
<tr>
<td>topManager</td>
<td>是</td>
<td>字符串</td>
<td>最高管理人</td>
</tr>
<tr>
<td>contact</td>
<td>是</td>
<td>字符串</td>
<td>联系人</td>
</tr>
<tr>
<td>contactInformation</td>
<td>是</td>
<td>字符串</td>
<td>联系人联系方式</td>
</tr>
<tr>
<td>unitProperty</td>
<td>是</td>
<td>字符串</td>
<td>单位性质</td>
</tr>
<tr>
<td>industry</td>
<td>是</td>
<td>字符串</td>
<td>所属行业</td>
</tr>
<tr>
<td>businessArea</td>
<td>是</td>
<td>字符串</td>
<td>经营范围</td>
</tr>
<tr>
<td>businessExpire</td>
<td>是</td>
<td>字符串</td>
<td>营业期限</td>
</tr>
<tr>
<td>captial</td>
<td>是</td>
<td>字符串</td>
<td>注册资本</td>
</tr>
<tr>
<td>staffCount</td>
<td>是</td>
<td>字符串</td>
<td>员工人数</td>
</tr>
<tr>
<td>productValue</td>
<td>是</td>
<td>字符串</td>
<td>近年产值</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>其它</td>
</tr>
<tr>
<td>resDesc</td>
<td>是</td>
<td>字符串</td>
<td>资源概要</td>
</tr>
<tr>
<td>abilityDesc</td>
<td>是</td>
<td>字符串</td>
<td>能力概要</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;memberName&quot;: &quot;1234&quot;,
    &quot;memberAbbr&quot;: &quot;&quot;,
    &quot;memberRegisterAddr&quot;: &quot;&quot;,
    &quot;memberAddr&quot;: &quot;&quot;,
    &quot;corporation&quot;: &quot;&quot;,
    &quot;topManager&quot;: &quot;&quot;,
    &quot;contact&quot;: &quot;&quot;,
    &quot;contactInformation&quot;: &quot;&quot;,
    &quot;unitProperty&quot;: &quot;&quot;,
    &quot;industry&quot;: &quot;&quot;,
    &quot;businessArea&quot;: &quot;&quot;,
    &quot;businessExpire&quot;: &quot;&quot;,
    &quot;captial&quot;: &quot;&quot;,
    &quot;staffCount&quot;: &quot;&quot;,
    &quot;productValue&quot;: &quot;&quot;,
    &quot;remark&quot;: &quot;&quot;,
    &quot;resDesc&quot;: &quot;&quot;,
    &quot;abilityDesc&quot;: &quot;&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;新增临时加盟商信息成功！&quot;
}
</code></pre>
<h3 id="-2-">注册阶段2：注册加盟商的任意一项能力信息</h3>
<p><strong>URL</strong>：</p>
<p>/api/member/register/stage2/hres</p>
<p>/api/member/register/stage2/sres</p>
<p>/api/member/register/stage2/tech</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<p>注册硬资源（hres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>humanResource</td>
<td>是</td>
<td>字符串</td>
<td>人力资源</td>
</tr>
<tr>
<td>facilityResource</td>
<td>是</td>
<td>字符串</td>
<td>设备资源</td>
</tr>
<tr>
<td>envResource</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>softResource</td>
<td>是</td>
<td>字符串</td>
<td>软资源</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>注册软资源（sres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>researchDirection</td>
<td>是</td>
<td>字符串</td>
<td>研究方向</td>
</tr>
<tr>
<td>teamResourceDesc</td>
<td>是</td>
<td>字符串</td>
<td>团队资源描述</td>
</tr>
<tr>
<td>teamBelongTo</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>abilityBase</td>
<td>是</td>
<td>字符串</td>
<td>能力基础</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>注册新技术/新材料（tech）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>abilityCategory</td>
<td>是</td>
<td>字符串</td>
<td>能力种类</td>
</tr>
<tr>
<td>proDirection</td>
<td>是</td>
<td>字符串</td>
<td>专业方向</td>
</tr>
<tr>
<td>principle</td>
<td>是</td>
<td>字符串</td>
<td>原理功能</td>
</tr>
<tr>
<td>achivement</td>
<td>是</td>
<td>字符串</td>
<td>项目成果</td>
</tr>
<tr>
<td>applyingArea</td>
<td>是</td>
<td>字符串</td>
<td>适用范围</td>
</tr>
<tr>
<td>coopIntention</td>
<td>是</td>
<td>字符串</td>
<td>合作意向</td>
</tr>
<tr>
<td>restriction</td>
<td>是</td>
<td>字符串</td>
<td>约束条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;abilityDescription&quot;:&quot;能力描述&quot;,
    &quot;humanResource&quot;:&quot;人力资源&quot;,
    &quot;facilityResource&quot;:&quot;设备资源&quot;,
    &quot;envResource&quot;:&quot;环境资源&quot;,
    &quot;softResource&quot;:&quot;软资源&quot;,
    &quot;otherResource&quot;:&quot;其他资源&quot;,
    &quot;sharingCondition&quot;:&quot;共享条件&quot;,
    &quot;costEstimate&quot;:&quot;成本估计&quot;,
    &quot;remark&quot;:&quot;备注&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;新增临时加盟商硬资源能力信息成功！&quot;
}
</code></pre>
<h3 id="-">查询注册阶段</h3>
<p><strong>URL</strong>：/api/member/register/stage</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前注册者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;您处于注册阶段的第1阶段&quot;,
    &quot;data&quot;: 1
}
</code></pre>
<h2 id="-">临时用户</h2>
<h3 id="-0-">注册阶段0：注册登录信息</h3>
<p><strong>URL</strong>：/api/user/register/stage0 </p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>name</td>
<td>是</td>
<td>字符串</td>
<td>注册用户名</td>
</tr>
<tr>
<td>password</td>
<td>是</td>
<td>字符串</td>
<td>注册用户密码</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;name&quot;:&quot;woodji&quot;,
    &quot;password&quot;:&quot;123&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;注册成功！&quot;,
    &quot;token&quot;: &quot;eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWdpc3RlciIsImF1ZCI6Indvb2RqaSIsImlhdCI6MTU3MDM3MDQ1MSwiZXhwIjoxNTcwMzk5MjUxfQ.82dluI0O_4CusWe0_6fkh3br3JEKWjHO4eBBHQdGayc&quot;
}
</code></pre>
<h3 id="-1-">注册阶段1：注册用户实体信息</h3>
<p><strong>URL</strong>：/api/user/register/stage1</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>userName</td>
<td>是</td>
<td>字符串</td>
<td>用户名称</td>
</tr>
<tr>
<td>userProperty</td>
<td>是</td>
<td>字符串</td>
<td>用户性质</td>
</tr>
<tr>
<td>userAddr</td>
<td>是</td>
<td>字符串</td>
<td>用户地址</td>
</tr>
<tr>
<td>userPhone</td>
<td>是</td>
<td>字符串</td>
<td>用户联系方式（固定电话）</td>
</tr>
<tr>
<td>userEmail</td>
<td>是</td>
<td>字符串</td>
<td>用户联系方式（邮箱）</td>
</tr>
<tr>
<td>contact</td>
<td>是</td>
<td>字符串</td>
<td>联系人</td>
</tr>
<tr>
<td>contactTelephone</td>
<td>是</td>
<td>字符串</td>
<td>联系人联系方式（手机号）</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{   
    &quot;userName&quot;:&quot;用户名称&quot;,
    &quot;userProperty&quot;:&quot;用户性质&quot;,
    &quot;userAddr&quot;:&quot;用户地址&quot;,
    &quot;userPhone&quot;:&quot;用户联系方式（固定电话）&quot;,
    &quot;userEmail&quot;:&quot;用户联系方式（邮箱）&quot;,
    &quot;contact&quot;:&quot;联系人&quot;,
    &quot;contactTelephone&quot;:&quot;联系人联系方式（手机号）&quot;,
    &quot;remark&quot;:&quot;备注&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;新增临时用户信息成功！&quot;
}
</code></pre>
<h3 id="-">查询注册阶段</h3>
<p><strong>URL</strong>：/api/member/register/stage</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前注册者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;您处于注册阶段的第1阶段&quot;,
    &quot;data&quot;: 1
}
</code></pre>
<h1 id="3-">3. 临时加盟商接口</h1>
<h2 id="-">查看自己的实体信息</h2>
<p><strong>URL</strong>：/api/member/temp/info</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 4,
        &quot;memberNo&quot;: &quot;2&quot;,
        &quot;memberName&quot;: &quot;woodjie&quot;,
        &quot;memberAddr&quot;: &quot;南京理工大学&quot;,
        &quot;memberPhone&quot;: &quot;110&quot;,
        &quot;memberMail&quot;: &quot;南京理工大学110号邮箱&quot;,
        &quot;memberEmail&quot;: &quot;110@110.com&quot;,
        &quot;contact&quot;: &quot;小明&quot;,
        &quot;contactPhone&quot;: &quot;120&quot;,
        &quot;unitProperty&quot;: &quot;大将军&quot;,
        &quot;industry&quot;: &quot;计算机专业&quot;,
        &quot;joinTime&quot;: &quot;2019-10-06&quot;,
        &quot;serviceTime&quot;: null,
        &quot;serviceSuccessTime&quot;: null,
        &quot;protocolUrl&quot;: &quot;这里是协议文件上传到服务器的地址&quot;
    }
}
</code></pre>
<h2 id="-">修改自己的实体信息</h2>
<p><strong>URL</strong>：/api/member/temp/info</p>
<p><strong>请求方式</strong>：PUT </p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>memberName</td>
<td>是</td>
<td>字符串</td>
<td>会员名称</td>
</tr>
<tr>
<td>memberAddr</td>
<td>是</td>
<td>字符串</td>
<td>会员地址</td>
</tr>
<tr>
<td>memberPhone</td>
<td>是</td>
<td>字符串</td>
<td>会员联系方式（电话）</td>
</tr>
<tr>
<td>memberMail</td>
<td>是</td>
<td>字符串</td>
<td>会员联系方式（邮箱）</td>
</tr>
<tr>
<td>memberEmail</td>
<td>是</td>
<td>字符串</td>
<td>会员联系方式（电子邮箱）</td>
</tr>
<tr>
<td>contact</td>
<td>是</td>
<td>字符串</td>
<td>联系人</td>
</tr>
<tr>
<td>contactPhone</td>
<td>是</td>
<td>字符串</td>
<td>联系人联系方式（手机号）</td>
</tr>
<tr>
<td>unitProperty</td>
<td>是</td>
<td>字符串</td>
<td>单位性质</td>
</tr>
<tr>
<td>industry</td>
<td>是</td>
<td>字符串</td>
<td>所属行业</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;memberName&quot;: &quot;woodjie实打实&quot;,
    &quot;memberAddr&quot;: &quot;南京理工大学&quot;,
    &quot;memberPhone&quot;: &quot;110&quot;,
    &quot;memberMail&quot;: &quot;南京理工大学110号邮箱&quot;,
    &quot;memberEmail&quot;: &quot;110@110.com&quot;,
    &quot;contact&quot;: &quot;小明&quot;,
    &quot;contactPhone&quot;: &quot;120&quot;,
    &quot;unitProperty&quot;: &quot;大将军&quot;,
    &quot;industry&quot;: &quot;计算机专业&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;更改成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 4,
        &quot;memberNo&quot;: &quot;2&quot;,
        &quot;memberName&quot;: &quot;woodjie实打实&quot;,
        &quot;memberAddr&quot;: &quot;南京理工大学&quot;,
        &quot;memberPhone&quot;: &quot;110&quot;,
        &quot;memberMail&quot;: &quot;南京理工大学110号邮箱&quot;,
        &quot;memberEmail&quot;: &quot;110@110.com&quot;,
        &quot;contact&quot;: &quot;小明&quot;,
        &quot;contactPhone&quot;: &quot;120&quot;,
        &quot;unitProperty&quot;: &quot;大将军&quot;,
        &quot;industry&quot;: &quot;计算机专业&quot;,
        &quot;joinTime&quot;: null,
        &quot;serviceTime&quot;: null,
        &quot;serviceSuccessTime&quot;: null,
        &quot;protocolUrl&quot;: null
    }
}
</code></pre>
<h2 id="-">查询能力信息</h2>
<p><strong>URL</strong>：/api/member/temp/ability</p>
<p><strong>请求方式</strong>：GET </p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;该用户能力信息不存在！&quot;
}
</code></pre>
<p>输出2：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 7,
        &quot;abilityNo&quot;: &quot;CThu Oct 10 21:06:29 CST 2019&quot;,
        &quot;memberNo&quot;: &quot;30&quot;,
        &quot;abilityDescription&quot;: &quot;能力描述&quot;,
        &quot;humanResource&quot;: &quot;人力资源&quot;,
        &quot;facilityResource&quot;: &quot;设备资源&quot;,
        &quot;envResource&quot;: &quot;环境资源&quot;,
        &quot;softResource&quot;: &quot;软资源&quot;,
        &quot;otherResource&quot;: &quot;其他资源&quot;,
        &quot;sharingCondition&quot;: &quot;共享条件&quot;,
        &quot;costEstimate&quot;: &quot;成本估计&quot;,
        &quot;remark&quot;: &quot;备注&quot;,
        &quot;joinTime&quot;: &quot;2019-10-10&quot;
    },
    &quot;type&quot;: &quot;hres&quot;
}
</code></pre>
<h2 id="-">修改能力信息</h2>
<p><strong>URL</strong>：</p>
<p>/api/member/temp/hres</p>
<p>/api/member/temp/sres</p>
<p>/api/member/temp/tech</p>
<p><strong>请求方式</strong>：PUT</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<p>修改硬资源（hres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>humanResource</td>
<td>是</td>
<td>字符串</td>
<td>人力资源</td>
</tr>
<tr>
<td>facilityResource</td>
<td>是</td>
<td>字符串</td>
<td>设备资源</td>
</tr>
<tr>
<td>envResource</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>softResource</td>
<td>是</td>
<td>字符串</td>
<td>软资源</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>修改软资源（sres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>researchDirection</td>
<td>是</td>
<td>字符串</td>
<td>研究方向</td>
</tr>
<tr>
<td>teamResourceDesc</td>
<td>是</td>
<td>字符串</td>
<td>团队资源描述</td>
</tr>
<tr>
<td>teamBelongTo</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>abilityBase</td>
<td>是</td>
<td>字符串</td>
<td>能力基础</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>修改新技术/新材料（tech）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>abilityCategory</td>
<td>是</td>
<td>字符串</td>
<td>能力种类</td>
</tr>
<tr>
<td>proDirection</td>
<td>是</td>
<td>字符串</td>
<td>专业方向</td>
</tr>
<tr>
<td>principle</td>
<td>是</td>
<td>字符串</td>
<td>原理功能</td>
</tr>
<tr>
<td>achivement</td>
<td>是</td>
<td>字符串</td>
<td>项目成果</td>
</tr>
<tr>
<td>applyingArea</td>
<td>是</td>
<td>字符串</td>
<td>适用范围</td>
</tr>
<tr>
<td>coopIntention</td>
<td>是</td>
<td>字符串</td>
<td>合作意向</td>
</tr>
<tr>
<td>restriction</td>
<td>是</td>
<td>字符串</td>
<td>约束条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;id&quot;: 7,
    &quot;abilityDescription&quot;:&quot;hahah&quot;,
    &quot;humanResource&quot;:&quot;人力资源&quot;,
    &quot;facilityResource&quot;:&quot;设备资源&quot;,
    &quot;envResource&quot;:&quot;环境资源&quot;,
    &quot;softResource&quot;:&quot;软资源&quot;,
    &quot;otherResource&quot;:&quot;其他资源&quot;,
    &quot;sharingCondition&quot;:&quot;共享条件&quot;,
    &quot;costEstimate&quot;:&quot;成本估计&quot;,
    &quot;remark&quot;:&quot;备注&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;修改成功！&quot;
}
</code></pre>
<h2 id="-">查询申请状态</h2>
<p><strong>URL</strong>：/api/member/temp/progress</p>
<p><strong>请求方式</strong>：GET </p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 3,
        &quot;transType&quot;: &quot;加盟申请&quot;,
        &quot;handlerId&quot;: null,
        &quot;transObjid&quot;: &quot;2&quot;,
        &quot;transStatus&quot;: &quot;审理中&quot;
    }
}
</code></pre>
<h2 id="-">重新提交申请</h2>
<p><strong>URL</strong>：/api/member/temp/reapply</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;请等待管理员审核完成！&quot;
}
</code></pre>
<p>输出2：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;提交成功！&quot;
}
</code></pre>
<h1 id="4-">4. 加盟商接口</h1>
<h2 id="-">实体信息查询与申请修改</h2>
<h3 id="-">查看自己的实体信息</h3>
<p><strong>URL</strong>：/api/member/info</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;该用户信息不存在！&quot;
}
</code></pre>
<p>输出2：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: {
        &quot;id&quot;: 1,
        &quot;memberNo&quot;: &quot;mem&quot;,
        &quot;memberName&quot;: &quot;&quot;,
        &quot;memberAbbr&quot;: &quot;&quot;,
        &quot;memberRegisterAddr&quot;: &quot;&quot;,
        &quot;memberAddr&quot;: &quot;&quot;,
        &quot;corporation&quot;: &quot;&quot;,
        &quot;topManager&quot;: &quot;&quot;,
        &quot;contact&quot;: &quot;&quot;,
        &quot;contactInformation&quot;: &quot;&quot;,
        &quot;unitProperty&quot;: &quot;&quot;,
        &quot;industry&quot;: &quot;&quot;,
        &quot;businessArea&quot;: &quot;&quot;,
        &quot;businessExpire&quot;: &quot;&quot;,
        &quot;captial&quot;: &quot;&quot;,
        &quot;staffCount&quot;: &quot;&quot;,
        &quot;productValue&quot;: &quot;&quot;,
        &quot;remark&quot;: &quot;&quot;,
        &quot;resDesc&quot;: &quot;&quot;,
        &quot;abilityDesc&quot;: &quot;&quot;,
        &quot;joinTime&quot;: &quot;1980-01-01&quot;,
        &quot;serviceTime&quot;: 0,
        &quot;serviceSuccessTime&quot;: 0,
        &quot;protocolUrl&quot;: &quot;&quot;
    }
}
</code></pre>
<h3 id="-">申请修改实体信息</h3>
<p><strong>URL</strong>：/api/member/info</p>
<p><strong>请求方式</strong>：PUT</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前登录者的token</p>
<p><strong>JSON参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>memberName</td>
<td>是</td>
<td>字符串</td>
<td>会员名称</td>
</tr>
<tr>
<td>memberAbbr</td>
<td>是</td>
<td>字符串</td>
<td>会员简称</td>
</tr>
<tr>
<td>memberRegisterAddr</td>
<td>是</td>
<td>字符串</td>
<td>会员注册地址</td>
</tr>
<tr>
<td>memberAddr</td>
<td>是</td>
<td>字符串</td>
<td>会员运营地址</td>
</tr>
<tr>
<td>corporation</td>
<td>是</td>
<td>字符串</td>
<td>法定代表人</td>
</tr>
<tr>
<td>topManager</td>
<td>是</td>
<td>字符串</td>
<td>最高管理人</td>
</tr>
<tr>
<td>contact</td>
<td>是</td>
<td>字符串</td>
<td>联系人</td>
</tr>
<tr>
<td>contactInformation</td>
<td>是</td>
<td>字符串</td>
<td>联系人联系方式</td>
</tr>
<tr>
<td>unitProperty</td>
<td>是</td>
<td>字符串</td>
<td>单位性质</td>
</tr>
<tr>
<td>industry</td>
<td>是</td>
<td>字符串</td>
<td>所属行业</td>
</tr>
<tr>
<td>businessArea</td>
<td>是</td>
<td>字符串</td>
<td>经营范围</td>
</tr>
<tr>
<td>businessExpire</td>
<td>是</td>
<td>字符串</td>
<td>营业期限</td>
</tr>
<tr>
<td>captial</td>
<td>是</td>
<td>字符串</td>
<td>注册资本</td>
</tr>
<tr>
<td>staffCount</td>
<td>是</td>
<td>字符串</td>
<td>员工人数</td>
</tr>
<tr>
<td>productValue</td>
<td>是</td>
<td>字符串</td>
<td>近年产值</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>其它</td>
</tr>
<tr>
<td>resDesc</td>
<td>是</td>
<td>字符串</td>
<td>资源概要</td>
</tr>
<tr>
<td>abilityDesc</td>
<td>是</td>
<td>字符串</td>
<td>能力概要</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;memberName&quot;: &quot;123&quot;,
    &quot;memberAbbr&quot;: &quot;&quot;,
    &quot;memberRegisterAddr&quot;: &quot;&quot;,
    &quot;memberAddr&quot;: &quot;&quot;,
    &quot;corporation&quot;: &quot;&quot;,
    &quot;topManager&quot;: &quot;&quot;,
    &quot;contact&quot;: &quot;&quot;,
    &quot;contactInformation&quot;: &quot;&quot;,
    &quot;unitProperty&quot;: &quot;&quot;,
    &quot;industry&quot;: &quot;&quot;,
    &quot;businessArea&quot;: &quot;&quot;,
    &quot;businessExpire&quot;: &quot;&quot;,
    &quot;captial&quot;: &quot;&quot;,
    &quot;staffCount&quot;: &quot;&quot;,
    &quot;productValue&quot;: &quot;&quot;,
    &quot;remark&quot;: &quot;&quot;,
    &quot;resDesc&quot;: &quot;&quot;,
    &quot;abilityDesc&quot;: &quot;&quot;
}
</code></pre>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;没有权限！&quot;
}
</code></pre>
<p>输出2：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;提交成功！等待审核中&quot;
}
</code></pre>
<h2 id="-">实体能力查询与申请修改</h2>
<h3 id="-">查看自己的能力</h3>
<p><strong>URL</strong>：</p>
<p>/api/member/hres</p>
<p>/api/member/sres</p>
<p>/api/member/tech</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>否</td>
<td>整型</td>
<td>要查询的能力id，当不传该参数时返回操作用户的所有能力</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;该用户不存在硬资源信息！&quot;
}
</code></pre>
<p>输出2：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: [
        {
            &quot;id&quot;: 1,
            &quot;abilityNo&quot;: &quot;hres1&quot;,
            &quot;memberNo&quot;: &quot;mem&quot;,
            &quot;abilityDescription&quot;: &quot;哈哈哈哈&quot;,
            &quot;humanResource&quot;: &quot;&quot;,
            &quot;facilityResource&quot;: &quot;&quot;,
            &quot;envResource&quot;: &quot;&quot;,
            &quot;softResource&quot;: &quot;&quot;,
            &quot;otherResource&quot;: &quot;&quot;,
            &quot;sharingCondition&quot;: &quot;&quot;,
            &quot;costEstimate&quot;: &quot;&quot;,
            &quot;remark&quot;: &quot;&quot;,
            &quot;joinTime&quot;: &quot;1980-01-01&quot;
        }
    ]
}
</code></pre>
<h3 id="-">请求新增自己的能力</h3>
<p><strong>URL</strong>：</p>
<p>/api/member/hres</p>
<p>/api/member/sres</p>
<p>/api/member/tech</p>
<p><strong>请求方式</strong>：POST</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<p>注册硬资源（hres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>humanResource</td>
<td>是</td>
<td>字符串</td>
<td>人力资源</td>
</tr>
<tr>
<td>facilityResource</td>
<td>是</td>
<td>字符串</td>
<td>设备资源</td>
</tr>
<tr>
<td>envResource</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>softResource</td>
<td>是</td>
<td>字符串</td>
<td>软资源</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>注册软资源（sres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>researchDirection</td>
<td>是</td>
<td>字符串</td>
<td>研究方向</td>
</tr>
<tr>
<td>teamResourceDesc</td>
<td>是</td>
<td>字符串</td>
<td>团队资源描述</td>
</tr>
<tr>
<td>teamBelongTo</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>abilityBase</td>
<td>是</td>
<td>字符串</td>
<td>能力基础</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>注册新技术/新材料（tech）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>abilityCategory</td>
<td>是</td>
<td>字符串</td>
<td>能力种类</td>
</tr>
<tr>
<td>proDirection</td>
<td>是</td>
<td>字符串</td>
<td>专业方向</td>
</tr>
<tr>
<td>principle</td>
<td>是</td>
<td>字符串</td>
<td>原理功能</td>
</tr>
<tr>
<td>achivement</td>
<td>是</td>
<td>字符串</td>
<td>项目成果</td>
</tr>
<tr>
<td>applyingArea</td>
<td>是</td>
<td>字符串</td>
<td>适用范围</td>
</tr>
<tr>
<td>coopIntention</td>
<td>是</td>
<td>字符串</td>
<td>合作意向</td>
</tr>
<tr>
<td>restriction</td>
<td>是</td>
<td>字符串</td>
<td>约束条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;abilityDescription&quot;:&quot;能力描述&quot;,
    &quot;humanResource&quot;:&quot;人力资源&quot;,
    &quot;facilityResource&quot;:&quot;设备资源&quot;,
    &quot;envResource&quot;:&quot;环境资源&quot;,
    &quot;softResource&quot;:&quot;软资源&quot;,
    &quot;otherResource&quot;:&quot;其他资源&quot;,
    &quot;sharingCondition&quot;:&quot;共享条件&quot;,
    &quot;costEstimate&quot;:&quot;成本估计&quot;,
    &quot;remark&quot;:&quot;备注&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;硬资源能力添加已申请！&quot;
}
</code></pre>
<h3 id="-">请求修改自己的能力</h3>
<p><strong>URL</strong>：</p>
<p>/api/member/hres</p>
<p>/api/member/sres</p>
<p>/api/member/tech</p>
<p><strong>请求方式</strong>：PUT</p>
<p><strong>请求头</strong>：</p>
<p>Content-Type：application/json</p>
<p>Token：当前注册者的token</p>
<p><strong>JSON参数</strong>：</p>
<p>修改硬资源（hres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>humanResource</td>
<td>是</td>
<td>字符串</td>
<td>人力资源</td>
</tr>
<tr>
<td>facilityResource</td>
<td>是</td>
<td>字符串</td>
<td>设备资源</td>
</tr>
<tr>
<td>envResource</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>softResource</td>
<td>是</td>
<td>字符串</td>
<td>软资源</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>修改软资源（sres）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>researchDirection</td>
<td>是</td>
<td>字符串</td>
<td>研究方向</td>
</tr>
<tr>
<td>teamResourceDesc</td>
<td>是</td>
<td>字符串</td>
<td>团队资源描述</td>
</tr>
<tr>
<td>teamBelongTo</td>
<td>是</td>
<td>字符串</td>
<td>环境资源</td>
</tr>
<tr>
<td>abilityBase</td>
<td>是</td>
<td>字符串</td>
<td>能力基础</td>
</tr>
<tr>
<td>otherResource</td>
<td>否</td>
<td>字符串</td>
<td>其他资源</td>
</tr>
<tr>
<td>sharingCondition</td>
<td>是</td>
<td>字符串</td>
<td>共享条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p>修改新技术/新材料（tech）时</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要修改的能力id</td>
</tr>
<tr>
<td>abilityDescription</td>
<td>是</td>
<td>字符串</td>
<td>能力描述</td>
</tr>
<tr>
<td>abilityCategory</td>
<td>是</td>
<td>字符串</td>
<td>能力种类</td>
</tr>
<tr>
<td>proDirection</td>
<td>是</td>
<td>字符串</td>
<td>专业方向</td>
</tr>
<tr>
<td>principle</td>
<td>是</td>
<td>字符串</td>
<td>原理功能</td>
</tr>
<tr>
<td>achivement</td>
<td>是</td>
<td>字符串</td>
<td>项目成果</td>
</tr>
<tr>
<td>applyingArea</td>
<td>是</td>
<td>字符串</td>
<td>适用范围</td>
</tr>
<tr>
<td>coopIntention</td>
<td>是</td>
<td>字符串</td>
<td>合作意向</td>
</tr>
<tr>
<td>restriction</td>
<td>是</td>
<td>字符串</td>
<td>约束条件</td>
</tr>
<tr>
<td>costEstimate</td>
<td>是</td>
<td>字符串</td>
<td>成本估算</td>
</tr>
<tr>
<td>remark</td>
<td>否</td>
<td>字符串</td>
<td>备注</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输入：</p>
<pre><code class="lang-json">{
    &quot;id&quot;:&quot;1&quot;,
    &quot;abilityDescription&quot;:&quot;能力描述h&quot;,
    &quot;humanResource&quot;:&quot;人力资源&quot;,
    &quot;facilityResource&quot;:&quot;设备资源&quot;,
    &quot;envResource&quot;:&quot;环境资源&quot;,
    &quot;softResource&quot;:&quot;软资源&quot;,
    &quot;otherResource&quot;:&quot;其他资源&quot;,
    &quot;sharingCondition&quot;:&quot;共享条件&quot;,
    &quot;costEstimate&quot;:&quot;成本估计&quot;,
    &quot;remark&quot;:&quot;备注&quot;
}
</code></pre>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;提交成功！等待审核中&quot;
}
</code></pre>
<h3 id="-">请求删除自己的能力</h3>
<p><strong>URL</strong>：</p>
<p>/api/member/hres</p>
<p>/api/member/sres</p>
<p>/api/member/tech</p>
<p><strong>请求方式</strong>：DELETE</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>否</td>
<td>整型</td>
<td>要删除的能力id</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输出1：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 1,
    &quot;msg&quot;: &quot;所要删除的硬资源信息不可见！&quot;
}
</code></pre>
<p>输出2（删除操作与修改操作冲突）：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;请等待与该项相关联的操作审理完成！&quot;
}
</code></pre>
<p>输出3：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;提交成功！等待审核中&quot;
}
</code></pre>
<h2 id="-">加盟商事务进度查询</h2>
<h3 id="-">查询事务进度</h3>
<p><strong>URL</strong>：/api/member/progress</p>
<p><strong>请求方式</strong>：GET</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;查询成功！&quot;,
    &quot;data&quot;: [
        {
            &quot;id&quot;: 4,
            &quot;transType&quot;: &quot;能力修改-删除&quot;,
            &quot;applicantNo&quot;: &quot;mem&quot;,
            &quot;applyTime&quot;: 1570607744000,
            &quot;handlerId&quot;: null,
            &quot;handleTime&quot;: null,
            &quot;transObjid&quot;: &quot;安达市多&quot;,
            &quot;transStatus&quot;: &quot;审理中&quot;,
            &quot;remark&quot;: null
        },
        {
            &quot;id&quot;: 3,
            &quot;transType&quot;: &quot;能力修改-更改&quot;,
            &quot;applicantNo&quot;: &quot;mem&quot;,
            &quot;applyTime&quot;: 1570607249000,
            &quot;handlerId&quot;: null,
            &quot;handleTime&quot;: null,
            &quot;transObjid&quot;: &quot;hres1&quot;,
            &quot;transStatus&quot;: &quot;审理中&quot;,
            &quot;remark&quot;: null
        },
        {
            &quot;id&quot;: 2,
            &quot;transType&quot;: &quot;能力修改-添加&quot;,
            &quot;applicantNo&quot;: &quot;mem&quot;,
            &quot;applyTime&quot;: 1570606604000,
            &quot;handlerId&quot;: null,
            &quot;handleTime&quot;: null,
            &quot;transObjid&quot;: &quot;C1009153643&quot;,
            &quot;transStatus&quot;: &quot;审理中&quot;,
            &quot;remark&quot;: null
        },
        {
            &quot;id&quot;: 1,
            &quot;transType&quot;: &quot;资料修改&quot;,
            &quot;applicantNo&quot;: &quot;mem&quot;,
            &quot;applyTime&quot;: 1570605895000,
            &quot;handlerId&quot;: null,
            &quot;handleTime&quot;: null,
            &quot;transObjid&quot;: &quot;mem&quot;,
            &quot;transStatus&quot;: &quot;审理中&quot;,
            &quot;remark&quot;: null
        }
    ]
}
</code></pre>
<h3 id="-">撤销事务</h3>
<p><strong>URL</strong>：/api/member/progress</p>
<p><strong>请求方式</strong>：DELETE</p>
<p><strong>请求头</strong>：</p>
<p>Token：当前登录者的token</p>
<p><strong>参数</strong>：</p>
<table>
<thead>
<tr>
<th>参数名</th>
<th>必选</th>
<th>类型</th>
<th>说明</th>
</tr>
</thead>
<tbody>
<tr>
<td>id</td>
<td>是</td>
<td>整型</td>
<td>要撤销的事务id</td>
</tr>
</tbody>
</table>
<p><strong>示例</strong>：</p>
<p>输出：</p>
<pre><code class="lang-json">{
    &quot;code&quot;: 0,
    &quot;msg&quot;: &quot;撤销成功！&quot;
}
</code></pre>

          	</article>
        </div>
		</div>
  </body>
</html>
<script type="text/javascript" src="/api/resource/toc/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/api/resource/toc/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="/api/resource/toc/js/ztree_toc.js"></script>
<script type="text/javascript" src="/api/resource/toc_conf.js"></script>

<SCRIPT type="text/javascript" >
<!--
$(document).ready(function(){
    var css_conf = eval(markdown_panel_style);
    $('#readme').css(css_conf)
    
    var conf = eval(jquery_ztree_toc_opts);
		$('#tree').ztree_toc(conf);
});
//-->
</SCRIPT>