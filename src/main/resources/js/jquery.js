$(()=>{
    /** gototop **/ 
    $(".gototop").click((e) => {
        e.preventDefault();
        $("html, body").animate({scrollTop : "0"}, 800)
    });
});