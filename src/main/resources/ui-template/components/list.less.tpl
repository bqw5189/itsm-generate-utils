.table {
  td.avatar {
    img {
      border-radius: 50%;
    }
  }

  :global {
    .ant-table-tbody > tr > td,
    .ant-table-thead > tr > th {
      height: 62px;
    }
  }

  &.motion {
    :global {
      .ant-table-tbody > tr > td,
      .ant-table-thead > tr > th {
       <#foreach from=$entity.fields item=field name=i#>
          &:nth-child(<#$smarty.foreach.i.index+1#>) {
            width: 10%;
          }
       <#/foreach#>
           &:nth-child(<#$smarty.foreach.i.index+2#>) {
             width: 10%;
           }
      }

      .ant-table-thead {
        & > tr {
          transition: none;
          display: block;

          & > th {
            display: inline-flex;
            align-items: center;
            justify-content: center;
          }
        }
      }

      .ant-table-tbody {
        & > tr {
          transition: none;
          display: block;
          border-bottom: 1px solid #f5f5f5;

          & > td {
            border-bottom: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
          }

          &.ant-table-expanded-row-level-1 > td {
            height: auto;
          }
        }
      }
    }
  }
}
