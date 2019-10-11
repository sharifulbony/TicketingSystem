<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->


<head>
    <meta charset="utf-8"/>
    <title>Simple Search Application</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <link rel="stylesheet" href="https://unpkg.com/vue-multiselect@2.1.0/dist/vue-multiselect.min.css">

    <style>
        table.v-table tbody td,
        table.v-table tbody th {
            height: 19px;
        }
    </style>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.2.6/vue.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.16.1/axios.min.js"></script>
    <%--        <script src="https://unpkg.com/vuetable-2@1.6.0"></script>--%>
    <script src="https://unpkg.com/vue-multiselect@2.1.0"></script>


</head>
<body>
<div id="app">

    <v-app id="inspire">

    <v-card
            color="grey lighten-4"
            flat
            height="200px"
            tile
    >
        <v-toolbar  dense dark>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title>Ticketing System</v-toolbar-title>

            <div class="flex-grow-1"></div>

            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>

            <v-btn icon>
                <v-icon>mdi-heart</v-icon>
            </v-btn>

            <v-btn icon dark>
                <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
        </v-toolbar>
    </v-card>

            <v-data-table style="margin-left: 5%!important;margin-right: 5%!important;margin-top: -5%!important;"
                    :headers="headers"
                    :search="search"

<%--                    :single-expand="singleExpand"--%>
<%--                    :expanded.sync="expanded"--%>
<%--                    item-key="id"--%>
<%--                    show-expand--%>

                    :dark="true"
                    :items="ticket"
                    :items-per-page="10"
                    :sort-by="['id', 'title','email']"
                    :sort-desc="[true, true,true]"
<%--                    :footer-props="{--%>
<%--                                showFirstLastPage: true,--%>
<%--                                firstIcon: 'mdi-arrow-collapse-left',--%>
<%--                                lastIcon: 'mdi-arrow-collapse-right',--%>
<%--                                prevIcon: 'mdi-minus',--%>
<%--                                nextIcon: 'mdi-plus'--%>
<%--                                }"--%>
                    multi-sort
            <%--     hide-default-header--%>
<%--                    loading loading-text="Loading... Please wait"--%>
                    class="elevation-1">
<%--                <template v-slot:top>--%>
<%--                    <v-toolbar flat color="white">--%>
<%--                        <v-toolbar-title>Expandable Table</v-toolbar-title>--%>
<%--                        <div class="flex-grow-1"></div>--%>
<%--                        <v-switch v-model="singleExpand" label="Single expand" class="mt-2"></v-switch>--%>
<%--                    </v-toolbar>--%>
<%--                </template>--%>
<%--                <template v-slot:expanded-item="{ headers }">--%>
<%--                    <td :colspan="headers.length">Peek-a-boo!</td>--%>
<%--                </template>--%>

<%--                <template v-slot:top>--%>
<%--                    <v-dialog--%>
<%--                            v-model="alert"--%>
<%--                            max-width="290"--%>
<%--                    >--%>
<%--                        <v-card>--%>
<%--                            <v-card-title class="headline">Use Google's location service?</v-card-title>--%>

<%--                            <v-card-text>--%>
<%--                                Let Google help apps determine location. This means sending anonymous location data to--%>
<%--                                Google, even when no apps are running.--%>
<%--                            </v-card-text>--%>

<%--                            <v-card-actions>--%>
<%--                                <div class="flex-grow-1"></div>--%>

<%--                                <v-btn--%>
<%--                                        color="green darken-1"--%>
<%--                                        text--%>
<%--                                        @click="dialog = false"--%>
<%--                                >--%>
<%--                                    Disagree--%>
<%--                                </v-btn>--%>

<%--                                <v-btn--%>
<%--                                        color="green darken-1"--%>
<%--                                        text--%>
<%--                                        @click="dialog = false"--%>
<%--                                >--%>
<%--                                    Agree--%>
<%--                                </v-btn>--%>
<%--                            </v-card-actions>--%>
<%--                        </v-card>--%>
<%--                    </v-dialog>--%>
<%--                </template>--%>

                <%--                    CRUD from--%>
                <template v-slot:top>
                    <v-toolbar flat color="red">
                        <v-toolbar-title>All tickets</v-toolbar-title>
                        <v-divider
                                class="mx-4"
                                inset
                                vertical
                        ></v-divider>
                        <div class="flex-grow-1"></div>
                        <v-dialog v-model="dialog" max-width="500px">
                            <template v-slot:activator="{ on }">
                                <v-btn color="success" dark class="mb-2" v-on="on">New Ticket</v-btn>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="headline">{{ formTitle }}</span>
                                </v-card-title>

                                <v-card-text>
                                    <v-container>
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-text-field
                                                        v-model="editedItem.title"
                                                        label="Title"
                                                        :rules="nameRules"
                                                        required
                                                >

                                                </v-text-field>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-text-field
                                                        v-model="editedItem.email"
                                                        label="Email"
                                                        :rules="emailRules"
                                                        required
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-textarea
                                                        v-model="editedItem.description"
                                                        label="Description"
                                                        :rules="nameRules"
                                                        required

                                                >

                                                </v-textarea>
                                            </v-col>
                                        </v-row>
                                        <v-row>
                                            <v-col cols="12" sm="6" md="12">
                                                <%--                                            <select id="priority_list" v-model="editedItem.priority">--%>
                                                <%--                                                <option v-for="item in priorities" :value="item.id">{{ item.text }}--%>
                                                <%--                                                </option>--%>
                                                <%--                                            </select>--%>
                                                <v-select
                                                        v-model="editedItem.priority"
                                                        :items="items"
                                                        item-text="text"
                                                        item-value="id"
                                                        :rules="[v => !!v || 'Item is required']"
                                                        label="Priority"
                                                <%--                                                    return-object--%>
                                                        single-line
                                                        required
                                                ></v-select>
                                            </v-col>


                                        </v-row>
                                    </v-container>
                                </v-card-text>

                                <v-card-actions>
                                    <div class="flex-grow-1"></div>
                                    <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                                    <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                    </v-toolbar>
                </template>
                <template v-slot:item.action="{ item }">
                    <v-icon
                            small
                            class="mr-2"
                            @click="editTicket(item)"
                    >
                        edit
                    </v-icon>
                    <v-icon
                            small
                            @click="closeTicket(item)"
                    >
                        delete
                    </v-icon>
                </template>
                <template v-slot:no-data>
                    <v-btn color="primary" @click="initialize">Reset</v-btn>
                </template>

            </v-data-table>

    </v-app>


        <v-footer color="indigo" app inset>
            <span class="white--text">&copy; Developed By:
                <a style="color:red;" href="https://www.linkedin.com/in/sharifulbony/">A K M Shariful Islam
                </a>
            </span>
        </v-footer>

</div>


<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vuetify/dist/vuetify.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/x.y.z/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/x.y.z/locale/ar.js"></script>

<script>
    let vue = new Vue({
        el: '#app',
        vuetify: new Vuetify(),
        moment:new Moment(),
        data() {
            return {
                // expanded: [],
                // singleExpand: false,
                search: '',
                // alert: false,
                dialog: false,
                headers: [
                    // {
                    //     text: 'All Tickets',
                    //     align: 'left',
                    //     sortable: false,
                    //     // value: 'id',
                    // },
                    // {text: 'ID', value: 'id'},
                    {text: 'Title', value: 'title'},
                    {text: 'Email', value: 'email'},
                    {text: 'Description', value: 'description'},
                    {text: 'Priority', value: 'priorityText'},
                    {text: 'Date', value: 'timestamp'},
                    {text: 'Status', value: 'status'},
                    {text: 'Actions', value: 'action', sortable: false},
                    // {text: '', value: 'data-table-expand'},
                ],
                items: [
                    {id: 1, text: "Critical"},
                    {id: 2, text: "Severe"},
                    {id: 3, text: "High"},
                    {id: 4, text: "Moderate"},
                    {id: 5, text: "Low"},


                ],
                nameRules: [
                    v => !!v || 'This field is required',
                ],
                emailRules: [
                    v => !!v || 'E-mail is required',
                    v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
                ],
                ticket: [

                ],
                editedIndex: -1,
                editedItem: {
                    id: 0,
                    title: '',
                    email: '',
                    description: '',
                    priority: 0,
                },
                defaultItem: {
                    id: 0,
                    title: '',
                    email: '',
                    description: '',
                    priority: 0,
                },
            }
        },
        computed: {
            formTitle() {
                return this.editedIndex === -1 ? 'New Ticket' : 'Edit Ticket'
            },
        },

        watch: {
            dialog(val) {
                val || this.close()
            },
        },

        created() {
            this.initialize()
        },
        mounted() {

        },
        methods: {
            initialize() {
                Promise.resolve(axios.get("/all-ticket")
                    .then(
                        function (result) {

                            vue.ticket = result.data;
                            vue.ticket.forEach(function (item) {
                              


                                if(item.priority==1){
                                    item.priorityText="Critical"
                                }
                                else if(item.priority==2){
                                    item.priorityText="Severe"

                                }
                                else if(item.priority==3){
                                    item.priorityText="High"

                                }
                                else if(item.priority==4){
                                    item.priorityText="Moderate"

                                }
                                else if(item.priority==5){
                                    item.priorityText="Low"
                                }

                                if(item.status==0){
                                    item.statusText="Open"
                                }

                            })
                        }
                    ).catch(

                    )
                );
            },

            editTicket(item) {
                this.editedIndex = this.ticket.indexOf(item);
                this.editedItem = Object.assign({}, item);
                this.dialog = true;
            },

            closeTicket(item) {
                // this.alert = true;
                const index = this.ticket.indexOf(item);
                confirm('Are you sure you want to close this ticket?') && this.ticket.splice(index, 1);
                postData("/close-ticket", item);
                // this.ticket.splice(index, 1);
            },

            close() {
                this.dialog = false;
                setTimeout(() => {
                    this.editedItem = Object.assign({}, this.defaultItem);
                    this.editedIndex = -1;
                }, 300)
            },


            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.ticket[this.editedIndex], this.editedItem);
                } else {
                    this.ticket.push(this.editedItem);
                }
                // debugger;
                postData("/save-ticket", this.editedItem);
                this.close();
            },


        },
    })

    // function getText(item){
    //     if(item==1){
    //         return "Severe";
    //     }
    //     else if(item==2){
    //         return "Very High";
    //     }
    //     if(item==3){
    //         return "High";
    //     }
    //     if(item==4){
    //         return "Moderate";
    //     }
    //     if(item==5){
    //         return "Low";
    //     }
    // }
    function postData(url, data) {

        axios.post(url, data)
            .then(
                function (result) {
                    // debugger;
                    // vue.ticket = result.data;
                    // result => (this.ticket = result)
                }
            ).catch(function (error) {

                toastr.error("Failed");

        });

    }
</script>

<%--<script src="../js/app.js"></script>--%>


</body>

</html>
